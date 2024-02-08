package com.example.applibre.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applibre.data.SuperHero
import com.example.applibre.network.SuperHeroApi
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.io.IOException

class HeroDeckViewModel:ViewModel(){
    var superHero:String by mutableStateOf("")
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
                superHero = superheroResponse.toString()

            }catch (e:IOException){

            }
        }
    }
}

