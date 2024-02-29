package com.example.applibre.data.model.db

import com.example.applibre.data.model.PowerStats
import com.google.gson.annotations.SerializedName


/**
 * SuperHeroe que se guarda en la base de datos
 * @param emailUser email del usuario
 * @param id id de la carta
 */
data class SuperHeroState(
    val emailUser:String = "",
    val id:String = "",
    val name: String = "",
    val image:String = "",
    @SerializedName("powerstats")
    val powerStats: PowerStats = PowerStats(),
    val idDoc: String = ""
)