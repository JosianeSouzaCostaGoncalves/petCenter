package com.example.petcentertwo.presenter.api

import com.example.petcentertwo.presenter.utils.Constants.Companion.CAT_URL
import com.example.petcentertwo.presenter.utils.Constants.Companion.DOG_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val retrofitDog by lazy {
        Retrofit.Builder()
            .baseUrl(DOG_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiDog: SimpleApi by lazy {
        retrofitDog.create(SimpleApi::class.java)
    }

    private val retrofitCat by lazy {

        Retrofit.Builder()
            .baseUrl(CAT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiCat: SimpleApi by lazy {
        retrofitCat.create(SimpleApi::class.java)
    }
}



