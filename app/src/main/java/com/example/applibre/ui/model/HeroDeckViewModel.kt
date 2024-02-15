package com.example.applibre.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applibre.data.model.Appearance
import com.example.applibre.data.model.Biography
import com.example.applibre.data.model.Connections
import com.example.applibre.data.model.Image
import com.example.applibre.data.model.Player
import com.example.applibre.data.model.PowerStats
import com.example.applibre.data.model.SuperHero
import com.example.applibre.data.model.Work
import com.example.applibre.network.SuperHeroApi
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.random.Random

/**
 * lógica del programa
 */
class HeroDeckViewModel:ViewModel(){
    private val _player = MutableLiveData<Player>()

    private val _nickNamePlayer = MutableLiveData<String>()
    val nickNamePlayer1: LiveData<String> = _nickNamePlayer

    private val _superHero  = MutableStateFlow<List<SuperHero>>(emptyList())
    val superHeroDeck: StateFlow<List<SuperHero>> = _superHero

    val lista: MutableList<SuperHero> = mutableListOf()

    var character by mutableStateOf(
        SuperHero(
            response = "",
            id = 0,
            name = "",
            powerStats = PowerStats(0, 0, 0, 0, 0, 0),
            biography = Biography("", "", listOf(), "", "", "", ""),
            appearance = Appearance("", "", listOf(), listOf(), "", ""),
            work = Work("", ""),
            connections = Connections("", ""),
            image = Image("")
        )
    )
    private set;


    init {
        getSuperHeroe()
    }

    fun onPlayer(nickName:String){
        _nickNamePlayer.value = nickName
    }


    fun getSuperHeroe(){
        //iniciamos una corrutina
        for(i in 0..2){
            viewModelScope.launch {
                try {
                    val numAleatorio = Random.nextInt(1, 732).toString()
                    val superHeroId = SuperHeroApi.retrofitService.getSuperHeroById(numAleatorio)
                    val gson = Gson()
                    val superheroResponse = gson.fromJson(superHeroId, SuperHero::class.java)
                    lista.add(superheroResponse)
                    _superHero.value = lista
                }catch (e:IOException){

                }
            }
        }
    }

}

