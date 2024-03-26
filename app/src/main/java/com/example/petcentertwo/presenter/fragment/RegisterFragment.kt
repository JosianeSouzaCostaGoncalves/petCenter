package com.example.petcentertwo.presenter.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.petcentertwo.R
import com.example.petcentertwo.databinding.FragmentRegisterBinding
import com.example.petcentertwo.presenter.data.db.AppDatabase
import com.example.petcentertwo.presenter.data.db.dao.RegisterPetDao
import com.example.petcentertwo.presenter.data.db.entity.RegisterPetEntity
import com.example.petcentertwo.presenter.data.db.repository.RepositoryDb
import com.example.petcentertwo.presenter.repository.Repository
import com.example.petcentertwo.presenter.utils.PetFragmentViewModel
import com.example.petcentertwo.presenter.utils.PetViewModelFactory


class RegisterFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var viewModel: PetFragmentViewModel
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(requireContext())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository()
        val repositoryDb = RepositoryDb(database.registerPetDao())
        val viewModelFactory = PetViewModelFactory(repository, repositoryDb)
        viewModel = ViewModelProvider(this, viewModelFactory)[PetFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onResume() {
        super.onResume()
        checkIsCatOrDog()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDatePicker()
        spinner()


        binding.floatingActionButton.setOnClickListener {
            checkIsCatOrDog()
        }

        binding.spnRegister.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    checkIsCatOrDog()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        binding.btnRegisterSalvar.setOnClickListener {
            viewModel.insert(
                RegisterPetEntity(
                    name = binding.etRegisterNome.text.toString(),
                    type = binding.spnRegister.selectedItem.toString(),
                    breed = binding.etRegisterBreed.text.toString(),
                    dateOfBrith = binding.etRegisterDate.text.toString(),
                    )
            )
            Toast.makeText(
                requireContext(),
                getString(R.string.cadastro),
                Toast.LENGTH_LONG
            ).show()
            requireView().findNavController().navigateUp()
        }
    }

    private fun setDatePicker() {
        binding.etRegisterDate.setOnClickListener {
            DatePickerFragment(this).show(childFragmentManager, DatePickerFragment.TAG)
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        binding.etRegisterDate.setText("" + day + "/" + month + "/" + year)
    }

    private fun setDogImage() {
        viewModel.getDogImage()
        viewModel.myDogResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Glide.with(requireContext())
                    .load(response.body()?.url.toString())
                    .into(binding.ivRandom)
            } else {
                Log.e("Response", response.errorBody().toString())
            }
        })
    }

    private fun setCatImage() {
        viewModel.getCatImage()
        viewModel.myCatResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Glide.with(requireContext())
                    .load(response.body()?.first()?.url.toString())
                    .into(binding.ivRandom)
            } else {
                Log.e("Response", response.errorBody().toString())
            }
        })
    }

    private fun spinner() {

        val typePets = resources.getStringArray(
            R.array.TypePets
        )
        binding.spnRegister.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            typePets
        )

    }

    private fun checkIsCatOrDog() {
        if (binding.spnRegister.selectedItem.toString() == "Cachorro") {
            binding.clRegisterImage.visibility = View.VISIBLE
            setDogImage()
        } else if (binding.spnRegister.selectedItem.toString() == "Gato") {
            binding.clRegisterImage.visibility = View.VISIBLE
            setCatImage()
        } else {
            binding.clRegisterImage.visibility = View.GONE
        }
    }
}