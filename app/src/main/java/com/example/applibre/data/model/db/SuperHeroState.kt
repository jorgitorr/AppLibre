package com.example.applibre.data.model.db

import com.example.applibre.data.model.PowerStats
import com.google.gson.annotations.SerializedName


/**
 * SuperHeroe que se guarda en la base de datos
 * @param emailUser email del usuario
 * @param id id del superheroe
 * @param name nombre del superherpe
 * @param image imagen de la carta
 * @param powerStats estadisticas del superheroe
 * @param idDoc que es la variable que recogemos de la base de datos
 * para poder eliminar la imagen (es una funcion que me queda por implementar)
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