package com.example.petcentertwo.presenter.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcentertwo.presenter.model.CatApiModel
import com.example.petcentertwo.presenter.model.DogApiModel
import com.example.petcentertwo.presenter.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class PetFragmentViewModel (
    private val repository: Repository
) : ViewModel() {

    val myDogResponse: MutableLiveData<Response<DogApiModel>> = MutableLiveData()
    val myCatResponse: MutableLiveData<Response<List<CatApiModel>>> = MutableLiveData()

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