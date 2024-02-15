package com.example.applibre.data.model

import com.google.gson.annotations.SerializedName

data class Appearance(
    val gender: String,
    val race: String,
    val height: List<String>,
    val weight: List<String>,
    @SerializedName("eye-color")
    val eyeColor: String,
    @SerializedName("hair-color")
    val hairColor: String
)
