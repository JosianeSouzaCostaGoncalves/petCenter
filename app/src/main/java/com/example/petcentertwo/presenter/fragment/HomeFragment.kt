package com.example.petcentertwo.presenter.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.petcentertwo.R
import com.example.petcentertwo.databinding.FragmentHomeBinding
import com.example.petcentertwo.presenter.CounterNotificationService
import com.example.petcentertwo.presenter.data.db.AppDatabase
import com.example.petcentertwo.presenter.data.db.entity.RegisterPetEntity
import com.example.petcentertwo.presenter.data.db.repository.RepositoryDb
import com.example.petcentertwo.presenter.repository.Repository
import com.example.petcentertwo.presenter.utils.PetFragmentViewModel
import com.example.petcentertwo.presenter.utils.PetViewModelFactory
import kotlinx.datetime.*


class HomeFragment : Fragment(), AdapterClass.RecyclerViewEvent {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PetFragmentViewModel
    private var itemsListBirthday: List<RegisterPetEntity> = emptyList()


    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(requireContext())
    }

    override fun onItemClick(position: Int, isDelete: Boolean) {
        if (isDelete) {
            viewModel.delete(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val service = CounterNotificationService(requireContext())
        birthdayNotification(service)
        val repository = Repository()
        val repositoryDb = RepositoryDb(database.registerPetDao())
        val viewModelFactory = PetViewModelFactory(repository, repositoryDb)
        viewModel = ViewModelProvider(this, viewModelFactory)[PetFragmentViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val service = CounterNotificationService(requireContext())
        setupPetList()
        birthdayNotification(service)

        binding.btnFab.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_homeFragment_to_registerFragment)
        }

        binding.tvWelcomeText.apply {
            text = resources.getString(R.string.history_title, arguments?.getString("userName"))
        }
    }

    private fun setupPetList() {
        viewModel.getItems()
        viewModel.itemEntitiesLiveData.observe(viewLifecycleOwner) {
            binding.rvMyPets.adapter = AdapterClass(dataList = it, listener = this)
            itemsListBirthday = it

        }
    }

    private fun birthdayNotification(service: CounterNotificationService) {
        val today = nowLocalDate().toDayMonth()
        val year = nowLocalDate().year

        itemsListBirthday.forEach {

            val date = it.dateOfBrith.substring(0, 5)
            val yearOld = year - it.dateOfBrith.substring(6, 10).toInt()
            if (date == today) {
                service.showBirthdayNotification(
                    it.name,
                    yearOld,
                )

            }
        }
    }

    fun nowLocalDate(): LocalDate {
        val now: Instant = Clock.System.now()
        return now.toLocalDateTime(TimeZone.currentSystemDefault()).date
    }

    fun LocalDate.toDayMonthYear(): String {
        val dayWithLeadingZero =
            if (this.dayOfMonth.toString().length < 2) "0${this.dayOfMonth}" else this.dayOfMonth
        val monthWithLeadingZero =
            if (this.monthNumber.toString().length < 2) "0${this.monthNumber}" else this.monthNumber
        return "$dayWithLeadingZero/$monthWithLeadingZero/${this.year}"
    }

    fun LocalDate.toDayMonth(): String {
        val dayWithLeadingZero =
            if (this.dayOfMonth.toString().length < 2) "0${this.dayOfMonth}" else this.dayOfMonth
        val monthWithLeadingZero =
            if (this.monthNumber.toString().length < 2) "0${this.monthNumber}" else this.monthNumber
        return "$dayWithLeadingZero/$monthWithLeadingZero"
    }
}