package com.example.petcentertwo.presenter.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petcentertwo.presenter.repository.Repository

class PetViewModelFactory (
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PetFragmentViewModel(repository) as T
    }
}