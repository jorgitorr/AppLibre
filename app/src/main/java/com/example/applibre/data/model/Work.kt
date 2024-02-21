package com.example.applibre.data.model

import com.google.gson.annotations.SerializedName

data class Work(
    val occupation: String = "",
    @SerializedName("base")
    val baseOfOperation: String = ""
)
