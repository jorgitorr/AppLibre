package com.example.applibre.data.model

import com.google.gson.annotations.SerializedName

/**
 * Trabajo del superheroe
 * @param occupation ocupacion del superheroe
 * @param baseOfOperation base de operaciones del superheroe
 */
data class Work(
    val occupation: String = "",
    @SerializedName("base")
    val baseOfOperation: String = ""
)
