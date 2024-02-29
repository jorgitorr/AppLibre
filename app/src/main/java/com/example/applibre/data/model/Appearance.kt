package com.example.applibre.data.model

import com.google.gson.annotations.SerializedName


/**
 * Apariencia del superheroe
 * @param gender genero del superheroe
 * @param race raza del superheroe
 * @param height altura del superheroe
 * @param weight peso del superheroe
 * @param eyeColor color de ojos del superheroe
 * @param hairColor color de pelo del superheroe
 */
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
