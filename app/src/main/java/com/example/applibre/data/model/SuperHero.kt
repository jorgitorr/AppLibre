package com.example.applibre.data.model

import com.google.gson.annotations.SerializedName

/**
 * Clase que contiene al superheroe
 * @param emailUser email del usuario
 * @param response respuesta
 * @param id id del superheroe
 * @param name nombre del superheroe
 * @param powerStats estadísticas del superheroe
 * @param biography biografia del superheroe
 * @param appearance apariencia del superheroe
 * @param work trabajo del superheroe
 * @param connections conexiones del superheroe
 * @param image imagen del superheroe, que contiene la url de la imagen
 */
data class SuperHero (
    val emailUser:String = "",
    val response:String = "",
    val id:String = "",
    val name:String = "",
    @SerializedName("powerstats")
    val powerStats: PowerStats = PowerStats(),
    val biography: Biography = Biography(),
    val appearance: Appearance = Appearance(),
    val work: Work = Work(),
    val connections: Connections = Connections(),
    val image: Image = Image()
){
    override fun toString(): String {
        return "SuperHero(response='$response', id='$id', name='$name', powerStats=$powerStats, biography=$biography, appearance=$appearance, work=$work, connections=$connections, image=$image)"
    }
}