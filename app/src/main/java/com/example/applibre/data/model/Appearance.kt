package com.example.applibre.data.model

import com.google.gson.annotations.SerializedName


data class Appearance(
    val gender: String = "",
    val race: String = "",
    val height: List<String> = listOf(),
    val weight: List<String> = listOf(),
    @SerializedName("eye-color")
    val eyeColor: String = "",
    @SerializedName("hair-color")
    val hairColor: String = ""
)
