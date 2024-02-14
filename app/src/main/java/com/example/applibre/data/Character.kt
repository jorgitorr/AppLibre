package com.example.applibre.data

import com.google.gson.annotations.SerializedName

data class Character (
    val response:String,
    val id:Int,
    val name:String,
    @SerializedName("powerstats")
    val powerStats:PowerStats,
    val biography:Biography,
    val appearance:Appearance,
    val work:Work,
    val connections: Connections,
    val image:Image,
)