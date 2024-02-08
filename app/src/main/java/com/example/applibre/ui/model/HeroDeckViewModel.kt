package com.example.applibre.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applibre.data.SuperHero
import com.example.applibre.network.SuperHeroApi
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
                val superHeroId = SuperHeroApi.retrofitService.getSuperHeroByName("batman")
                val superHeroInfo = SuperHeroApi.retrofitService.getSuperHeroById(superHeroId)
                superHero = superHeroId

            }catch (e:IOException){

            }
        }
    }
}

