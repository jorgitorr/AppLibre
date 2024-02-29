package com.example.applibre.data.model

import com.google.gson.annotations.SerializedName

/**
 * Conexiones del personaje
 * @param groupAffiliation afiliacion a un grupo, ej, Xmen
 * @param relatives personas con las que ha salido o que han sido de confianza para él
 */
data class Connections(
    @SerializedName("group-affilation")
    val groupAffiliation: String = "",
    val relatives: String = ""
)
