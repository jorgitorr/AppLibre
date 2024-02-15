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
)