package com.example.petcentertwo.presenter.model

import com.google.gson.annotations.SerializedName


class CatApiModel(

    @SerializedName("id")
    val idCat: String,
    val url: String,
    @SerializedName("width")
    val widthCat: Int,
    @SerializedName("heigth")
    val heigthCat: Int,
    val breeds: List<BreedsModel>
) {

    class BreedsModel(

        val weigth: List<WeigthModel>,
        @SerializedName("id")
        val idBreeds: String,
        val temperament: String,
        val origin: String,
        @SerializedName("country_codes")
        val countryCodes: String,
        @SerializedName("country_code")
        val countryCode: String,
        @SerializedName("life_span")
        val lifeSpan: String,
        @SerializedName("wikipedia_url")
        val wikipediaUrl: String,
        )

    class WeigthModel(

        val imperial: String,
        val metric: String,
        )
}