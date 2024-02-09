package com.example.applibre.data

import com.google.gson.annotations.SerializedName

data class Connections(
    @SerializedName("group-affilation")
    val groupAffiliation: String,
    val relatives: String
)
