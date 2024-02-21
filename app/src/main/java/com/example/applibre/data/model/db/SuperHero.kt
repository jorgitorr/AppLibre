package com.example.applibre.data.model.db

import com.example.applibre.data.model.Appearance
import com.example.applibre.data.model.Biography
import com.example.applibre.data.model.Connections
import com.example.applibre.data.model.Image
import com.example.applibre.data.model.PowerStats
import com.example.applibre.data.model.Work
import com.google.gson.annotations.SerializedName

data class SuperHero(
    val key:String,
    val id:String,
    val name:String,
    @SerializedName("powerstats")
    val powerStats: PowerStats,
    val biography: Biography,
    val appearance: Appearance,
    val work: Work,
    val connections: Connections,
    val image: Image
)
