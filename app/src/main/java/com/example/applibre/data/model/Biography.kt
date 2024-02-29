package com.example.applibre.data.model

import com.google.gson.annotations.SerializedName


/**
 * Biografia del superheroe
 * @param fullName nombre completo del superheroe
 * @param alterEgos alter ego del superheroe
 * @param aliases alias del superheroe
 * @param placeOfBirth lugar de nacimiento del superheroe
 * @param firstAppearance primera apariencia (en comic o serie) que ha tenido el superheroe
 * @param publisher publicados. ej: DC Comics, Marvel Comics
 * @param alignment good or bad -> si es bueno o malo
 */
data class Biography(
    @SerializedName("full-name")
    val fullName: String = "",
    @SerializedName("alter-egos")
    val alterEgos: String = "",
    val aliases: List<String> = listOf(),
    @SerializedName("place-of-birth")
    val placeOfBirth: String = "",
    @SerializedName("first-appearance")
    val firstAppearance: String = "",
    val publisher: String = "",
    val alignment: String = ""
)
