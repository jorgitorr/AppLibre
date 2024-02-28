package com.example.applibre.data.model.db

import com.example.applibre.data.model.Image
import com.example.applibre.data.model.PowerStats
import com.google.gson.annotations.SerializedName

data class SuperHeroState(
    val emailUser:String = "",
    val id:String = "",
    val name: String = "",
    val image:String = "",
    @SerializedName("powerstats")
    val powerStats: PowerStats = PowerStats()
)