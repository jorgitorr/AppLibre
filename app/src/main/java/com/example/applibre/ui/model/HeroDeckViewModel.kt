package com.example.applibre.ui.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applibre.data.model.Appearance
import com.example.applibre.data.model.Biography
import com.example.applibre.data.model.Connections
import com.example.applibre.data.model.Image
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
    /**
     * @param _superHero lista donde guarda el jugador sus superheroes
     * hay dos listas ya que cada una es para un jugador
     * @param superHeroDeck es la variable visible que referencia a _superHero
     * @param listaId es la lista de superheroes que podemos tener entre las cartas
     * @param lista guarda los superheroes en una lista para después pasarlo a la final
     */

    private val _superHero  = MutableStateFlow<List<SuperHero>>(emptyList())
    val superHeroDeck: StateFlow<List<SuperHero>> = _superHero


    private val _superHero2  = MutableStateFlow<List<SuperHero>>(emptyList())
    val superHeroDeck2: StateFlow<List<SuperHero>> = _superHero2


    /**    ID -> NOMBRE SUPERHEROE
     *     70->batman, 655->superman, 485->naruto, 215->Deathlok, 201->daredevil, 435->masterchief,
     *     423->magneto, 620->spiderman, 489->nickfury, 10->agente bob, 263->flash, 280->Ghost Rider,
     *     43->Ares, 52->Atom Girl, 298->Green Arrow, 309->Harley Queen, 311->Havok, 322->HellBoy,
     *     345->Fire Man, 213->Deathpool, 670->Toad, 538->Ra's Al Ghu, 550->Red Skull
     * */

    val listaId = listOf(70, 655, 485, 215, 201, 435, 423, 620, 489,
        10,370, 263, 280, 43, 52, 298, 309, 311, 322, 345, 655, 213,
        670, 538, 550)

    private val lista: MutableList<SuperHero> = mutableListOf()

    private val lista2: MutableList<SuperHero> = mutableListOf()

    var actualSuperHero by mutableStateOf(
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
        getSuperHeroes()
    }


    /**
     * obtiene 4 cartas para el jugador
     */
    fun getSuperHeroes(){
        //iniciamos una corrutina
        for (i in 0..3) {
            viewModelScope.launch {
                try {
                    //en vez de pasarle un número aleatorio recorro la lista con los id ya combrobados
                    //que tienen imagenes y demás
                    val numAleatorio = Random.nextInt(0, listaId.size-1)
                    val idAleatorio = listaId[numAleatorio].toString()
                    val superHeroId = SuperHeroApi.retrofitService.getSuperHeroById(idAleatorio)

                    val gson = Gson()
                    val superheroResponse = gson.fromJson(superHeroId, SuperHero::class.java)
                    if(superheroResponse.response=="success"){
                        lista.add(superheroResponse)
                        _superHero.value = lista
                    }
                }catch (e:IOException){
                    Log.d("getSuperHeroes: ", e.message.toString())
                }
            }
        }
    }

    /**
     * devuelve el superHeroe actual
     */
    fun findById(id:String):SuperHero{
        for(l in lista){
            if(l.id == id){
                 actualSuperHero = l
            }
        }
        return actualSuperHero
    }

    fun combatir(superHero: SuperHero, superHero2:SuperHero){
        val powerStats1 = superHero.powerStats
        val powerStats2 = superHero2.powerStats

        powerStats1.durability = (powerStats2.strength.toInt() - powerStats1.durability.toInt()).toString()
        powerStats2.durability = (powerStats1.strength.toInt() - powerStats2.durability.toInt()).toString()

        if(powerStats1.durability.toInt()<=0){
            superHero.vida = false
        }else if(powerStats2.durability.toInt()<=0){
            superHero2.vida = false
        }
    }


}



