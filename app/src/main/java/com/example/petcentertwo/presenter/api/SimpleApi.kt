package com.example.petcentertwo.presenter.api

import com.example.petcentertwo.presenter.model.CatApiModel
import com.example.petcentertwo.presenter.model.DogApiModel
import retrofit2.Response

import retrofit2.http.GET

interface SimpleApi {

    @GET("api/breeds/image/random")
    suspend fun getRandomDog(): Response<DogApiModel>

    @GET("images/search")
    suspend fun getRandomCat(): Response<List<CatApiModel>>

}