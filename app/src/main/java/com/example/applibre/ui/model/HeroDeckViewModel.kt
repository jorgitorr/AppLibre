package com.example.applibre.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applibre.data.Appearance
import com.example.applibre.data.Biography
import com.example.applibre.data.Connections
import com.example.applibre.data.Deck
import com.example.applibre.data.Image
import com.example.applibre.data.Player
import com.example.applibre.data.PowerStats
import com.example.applibre.data.SuperHero
import com.example.applibre.data.Work
import com.example.applibre.network.SuperHeroApi
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.random.Random

/**
 * lógica del programa
 */
class HeroDeckViewModel:ViewModel(){

    private lateinit var _player1: Player
    private lateinit var _player2: Player
    private var _turno = true
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
        getSuperHeroe()
        //newDeckHeroes()
    }

    fun onPlayer(){

    }


    /**
     * incializa el mazo del jugador
     * 5 cartas para cada jugador
     */
    private fun newDeckHeroes(){
        for (i in 1..10){
            if(_turno){
                _player1.superHeroes.add(getSuperHeroe2())
            }else{
                _player2.superHeroes.add(getSuperHeroe2())
            }
            _turno = !_turno
        }
    }

    private fun getSuperHeroe(){
        //iniciamos una corrutina
        viewModelScope.launch {
            try {
                val numAleatorio = Random.nextInt(1, 732).toString()
                val superHeroId = SuperHeroApi.retrofitService.getSuperHeroById(numAleatorio).trimIndent()
                val gson = Gson()
                val superheroResponse = gson.fromJson(superHeroId, SuperHero::class.java)
                superHero = superheroResponse

            }catch (e:IOException){

            }
        }
    }


    fun getSuperHeroe2():SuperHero{
        //iniciamos una corrutina
        viewModelScope.launch {
            try {
                val numAleatorio = Random.nextInt(1, 732).toString()
                val superHeroId = SuperHeroApi.retrofitService.getSuperHeroById(numAleatorio).trimIndent()
                val gson = Gson()
                val superheroResponse = gson.fromJson(superHeroId, SuperHero::class.java)
                superHero = superheroResponse

            }catch (e:IOException){

            }
        }

        return superHero
    }

    /**
     * calcula el poder de los personajes
     */
    private fun checkPuntos(){
        val powerStats = superHero.powerStats
        val totalPowers = powerStats.power + powerStats.combat + powerStats.durability + powerStats.speed
        + powerStats.strength + powerStats.intelligence

        val powerPercentage = powerStats.power.toDouble() / totalPowers * 10
        val combatPercentage = powerStats.combat.toDouble() / totalPowers * 15
        val durabilityPercentage = powerStats.durability.toDouble() / totalPowers * 15
        val speedPercentage = powerStats.speed.toDouble() / totalPowers * 15
        val strengthPercentage = powerStats.strength.toDouble() / totalPowers * 20
        val intelligencePercentage = powerStats.intelligence.toDouble() / totalPowers * 25

        /*
        * cada turno da mana
        * y empiezas con uno de mana
        * cada carta tiene su propio coste de mana
        * */


    }

}

