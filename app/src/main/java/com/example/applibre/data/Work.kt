package com.example.applibre.data

import com.google.gson.annotations.SerializedName

data class Work(
    val occupation: String,
    @SerializedName("base")
    val baseOfOperation: String
)
