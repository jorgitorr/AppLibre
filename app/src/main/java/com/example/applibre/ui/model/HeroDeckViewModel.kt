package com.example.applibre.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applibre.data.Appearance
import com.example.applibre.data.Biography
import com.example.applibre.data.Connections
import com.example.applibre.data.Image
import com.example.applibre.data.PowerStats
import com.example.applibre.data.SuperHero
import com.example.applibre.data.Work
import com.example.applibre.network.SuperHeroApi
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * lógica del programa
 */
class HeroDeckViewModel:ViewModel(){
    var superHero by mutableStateOf(SuperHero(
        id = 0,
        name = "",
        powerStats = PowerStats(0, 0, 0, 0, 0, 0),
        biography = Biography("", "", listOf(), "", "", "", ""),
        appearance = Appearance("", "", listOf(), listOf(), "", ""),
        work = Work("", ""),
        connections = Connections("", ""),
        image = Image("")
    ))
    private set;



    init {
        getSuperHeroes()
    }

    private fun getSuperHeroes(){
        //iniciamos una corrutina
        viewModelScope.launch {
            try {
                val superHeroId = SuperHeroApi.retrofitService.getSuperHeroById("70").trimIndent()
                val gson = Gson()
                val superheroResponse = gson.fromJson(superHeroId, SuperHero::class.java)
                superHero = superheroResponse

            }catch (e:IOException){

            }
        }
    }

    private fun checkPuntos(){

    }
}

