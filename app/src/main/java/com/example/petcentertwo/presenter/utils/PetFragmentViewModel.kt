package com.example.petcentertwo.presenter.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcentertwo.presenter.data.db.entity.RegisterPetEntity
import com.example.petcentertwo.presenter.data.db.repository.RepositoryDb
import com.example.petcentertwo.presenter.model.CatApiModel
import com.example.petcentertwo.presenter.model.DogApiModel
import com.example.petcentertwo.presenter.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.Response

class PetFragmentViewModel (
    private val repository: Repository,
    private val repositoryDb: RepositoryDb
) : ViewModel() {
    
    val myDogResponse: MutableLiveData<Response<DogApiModel>> = MutableLiveData()
    val myCatResponse: MutableLiveData<Response<List<CatApiModel>>> = MutableLiveData()

    val  itemEntitiesLiveData = MutableLiveData<List<RegisterPetEntity>>()

    private val _PetsList = MutableLiveData<List<RegisterPetEntity>>()
    val PetsList: MutableLiveData<List<RegisterPetEntity>>
        get() = _PetsList


    fun getItems() {
        viewModelScope.launch {
            repositoryDb.getAllRegisterPet().collect{ items ->
                itemEntitiesLiveData.postValue(items)
            }
        }
    }

    fun insert(registerPetEntity: RegisterPetEntity) {
        viewModelScope.launch {
            repositoryDb.insert(registerPetEntity)
        }
    }

    fun delete(registerPetEntity: RegisterPetEntity) {
        viewModelScope.launch {
            repositoryDb.delete(registerPetEntity.id)
        }
    }



    fun getDogImage() {
        viewModelScope.launch {
            val response: Response<DogApiModel> = repository.getRandomDogImage()
            if (response.isSuccessful) {
                myDogResponse.value = response
            }
        }
    }

    fun getCatImage() {
        viewModelScope.launch {
            val response: Response<List<CatApiModel>> = repository.getRandomCatImage()
            if (response.isSuccessful) {
                myCatResponse.value = response
            }
        }
    }



}