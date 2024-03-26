package com.example.petcentertwo.presenter.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petcentertwo.presenter.data.db.repository.RepositoryDb
import com.example.petcentertwo.presenter.repository.Repository

class PetViewModelFactory (
    private val repository: Repository,
    private val repositoryDb: RepositoryDb,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PetFragmentViewModel(repository,repositoryDb) as T
    }
}