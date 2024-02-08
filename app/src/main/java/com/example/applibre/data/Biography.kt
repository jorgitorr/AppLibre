package com.example.applibre.data

import com.google.gson.annotations.SerializedName

data class Biography(
    @SerializedName("full-name")
    val fullName: String,
    @SerializedName("alter-egos")
    val alterEgos: String,
    val aliases: List<String>,
    @SerializedName("place-of-birth")
    val placeOfBirth: String,
    @SerializedName("first-appearance")
    val firstAppearance: String,
    val publisher: String,
    val alignment: String
)
