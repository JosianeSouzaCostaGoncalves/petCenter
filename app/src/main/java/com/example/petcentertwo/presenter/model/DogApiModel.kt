package com.example.petcentertwo.presenter.model

import com.google.gson.annotations.SerializedName

data class DogApiModel(
    @SerializedName("message")
    val url: String,
    val status: String
)