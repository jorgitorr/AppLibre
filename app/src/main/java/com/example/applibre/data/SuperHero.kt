package com.example.applibre.data

import com.google.gson.annotations.SerializedName

data class SuperHero (
    val id:Int,
    @SerializedName("powerstats")
    val powerStats:PowerStats,
    @SerializedName("biography")
    val biography:Biography,
    val appearance:Appearance,
    val work:Work,
    val image:Image,
    val name:String
)