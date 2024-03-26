package com.example.petcentertwo.presenter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.petcentertwo.R
import com.example.petcentertwo.databinding.FragmentHomeBinding
import com.example.petcentertwo.presenter.data.db.AppDatabase
import com.example.petcentertwo.presenter.data.db.dao.RegisterPetDao
import com.example.petcentertwo.presenter.data.db.entity.RegisterPetEntity
import com.example.petcentertwo.presenter.data.db.repository.RepositoryDb
import com.example.petcentertwo.presenter.repository.Repository
import com.example.petcentertwo.presenter.utils.PetFragmentViewModel
import com.example.petcentertwo.presenter.utils.PetViewModelFactory

class HomeFragment : Fragment(), AdapterClass.RecyclerViewEvent {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PetFragmentViewModel

    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(requireContext())
    }

    val item: List<RegisterPetEntity> = listOf()
    override fun onItemClick(position: Int) {
        viewModel.delete(item[position])
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

        val repository = Repository()
        val repositoryDb = RepositoryDb(database.registerPetDao())
        val viewModelFactory = PetViewModelFactory(repository, repositoryDb)
        viewModel = ViewModelProvider(this, viewModelFactory)[PetFragmentViewModel::class.java]
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPetList()


        binding.btnFab.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_homeFragment_to_registerFragment)
        }
//        binding.tvWelcomeText.apply {
//            text = resources.getString(R.string.history_title, arguments?.getString("userName"))
//        }

    }

    private fun setupPetList() {
        viewModel.getItems()
        viewModel.itemEntitiesLiveData.observe(viewLifecycleOwner) {
            binding.rvMyPets.adapter = AdapterClass(dataList = it, listener = this)
        }
    }

}