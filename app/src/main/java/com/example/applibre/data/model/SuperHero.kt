package com.example.applibre.data.model

import com.google.gson.annotations.SerializedName

data class SuperHero (
    val response:String,
    val id:String,
    val name:String,
    @SerializedName("powerstats")
    val powerStats: PowerStats,
    val biography: Biography,
    val appearance: Appearance,
    val work: Work,
    val connections: Connections,
    val image: Image,
){
    override fun toString(): String {
        return "SuperHero(response='$response', id='$id', name='$name', powerStats=$powerStats, biography=$biography, appearance=$appearance, work=$work, connections=$connections, image=$image)"
    }
}