package com.example.applibre.ui.model

import android.util.Log
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
    private val _superHero  = MutableStateFlow<List<SuperHero>>(emptyList())
    val superHeroDeck: StateFlow<List<SuperHero>> = _superHero

    //70 batman, 655 superman, 485 naruto, 215 robot, 201 daredevil,
    // 435 masterchief, 423 magneto, 620 spiderman, nickfury 489
    //agente bob -> 10
    private val listaSuperHeroes = listOf(70,655,485,215,201, 435, 423, 620, 489,
        10,370)//recorrer la lista
    private val lista: MutableList<SuperHero> = mutableListOf()

    var character by mutableStateOf(
        SuperHero(
            response = "",
            id = "0",
            name = "",
            powerStats = PowerStats("0", "0", "0", "0", "0", "0"),
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


    fun getSuperHeroe(){
        //iniciamos una corrutina
        for (i in 0..3) {
            viewModelScope.launch {
                try {
                    //en vez de pasarle un número aleatorio recorro la lista con los id ya combrobados
                    //que tienen imagenes y demás
                    val numAleatorio = Random.nextInt(1, 732).toString()
                    val superHeroId = SuperHeroApi.retrofitService.getSuperHeroById(numAleatorio)

                    val gson = Gson()
                    val superheroResponse = gson.fromJson(superHeroId, SuperHero::class.java)
                    if(superheroResponse.response=="success"){
                        lista.add(superheroResponse)
                        _superHero.value = lista
                    }
                    else{
                        Log.d("Jorge: ","ERROR ${superheroResponse.response}")
                    }
                }catch (e:IOException){
                    Log.e("Error: ",e.message.toString())
                }
            }
        }
        Log.d("lista",lista.size.toString())
    }
}

