package com.example.petcentertwo.presenter.repository

import com.example.petcentertwo.presenter.api.RetrofitInstance
import com.example.petcentertwo.presenter.model.CatApiModel
import com.example.petcentertwo.presenter.model.DogApiModel
import retrofit2.Response

class Repository {

    suspend fun getRandomDogImage() : Response<DogApiModel> {
        return RetrofitInstance.apiDog.getRandomDog()
    }
    suspend fun getRandomCatImage() : Response<List<CatApiModel>> {
        return RetrofitInstance.apiCat.getRandomCat()
    }
}