package com.example.applibre.data.model

import com.google.gson.annotations.SerializedName

data class SuperHero (
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