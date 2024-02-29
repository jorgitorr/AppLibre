package com.example.applibre.data.model


/**
 * estadísticas del superheroe
 * @param intelligence inteligencia del superheroe
 * @param strength fuerza del superheroe
 * @param speed velocidad del superheroe
 * @param durability resistencia del superheroe
 * @param power poder del superheroe
 * @param combat combate del superheroe
 */
data class PowerStats(
    val intelligence:String = "",
    val strength: String = "",
    val speed: String = "",
    var durability: String = "",
    val power: String = "",
    val combat: String = ""
)