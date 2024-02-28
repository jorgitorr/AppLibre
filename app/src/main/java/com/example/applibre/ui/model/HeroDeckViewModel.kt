package com.example.applibre.ui.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applibre.data.model.SuperHero
import com.example.applibre.data.model.db.SuperHeroState
import com.example.applibre.network.SuperHeroApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import kotlin.random.Random

/**
 * lógica de las cartas y de los SuperHeroes
 */
class HeroDeckViewModel:ViewModel(){
    /**
     * @param auth Firebase Authentication
     * @param firestore Inicializa firestore con una instancia del cliente de Firestore.
     * @param _superHero lista donde guarda el jugador sus superheroes
     * @param superHeroDeck es la variable visible que referencia a _superHero
     * @param superHeroesDC contiene superHeroes de la lista de DC
     * @param superHeroresMarvel contiene superHeroes de la lista de Marvel
     * @param lista guarda los superheroes en una lista para después pasarlo a la final
     * @param actualSuperHero superHeroe seleccionado actual
     * @param listaId contiene id de los superheroes que podemos tener entre las cartas
     * @param listaIdDc contiene id de superheroes de DC
     * @param listaMarvel contiene id de superheroes de Marvel
     * @param showAlert te muestra o no la pantalla de salir
     * contiene los id de los superheroes y estos son los que contiene:
     *     ID -> nombre superHeroe
     *     70->batman, 655->superman, 485->naruto, 215->Deathlok, 201->daredevil, 435->master chief,
     *     423->magneto, 620->spiderman, 489->nick fury, 10->agent bob, 263->flash, 280->Ghost Rider,
     *     43->Ares, 52->Atom Girl, 298->Green Arrow, 309->Harley Queen, 311->Havok, 322->HellBoy,
     *     345->Fire Man, 213->Death-pool, 670->Toad, 538->Ra's Al Ghu, 550->Red Skull
     *     720 -> Wonder Woman, 491 -> NigthWing, 165 -> Catwoman, 194 -> Cyborg, 38 -> Aquaman,
     *     432 -> Martian manhunter, 132 -> Booster Gold, 367 -> John Constantine
     * */

    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private val firestore = Firebase.firestore

    private val _superHeroesDC  = MutableStateFlow<List<SuperHero>>(emptyList())
    val superHeroDeckDC: StateFlow<List<SuperHero>> = _superHeroesDC

    private val _superHeroesMarvel  = MutableStateFlow<List<SuperHero>>(emptyList())
    val superHeroDeckMarvel: StateFlow<List<SuperHero>> = _superHeroesMarvel

    private val _superHeroDeckPlayer = MutableStateFlow<List<SuperHeroState>>(emptyList())
    val superHeroDeckPlayer: StateFlow<List<SuperHeroState>> =  _superHeroDeckPlayer

    private val lista: MutableList<SuperHero> = mutableListOf()
    private var actualSuperHero by mutableStateOf(SuperHero())
        private set;

    private val listaIdDc = listOf(70, 655, 52, 298, 538, 720, 491, 165, 194, 38, 432, 132, 367 )
    private val listaIdMarvel = listOf(215, 201, 423, 620, 489, 10, 263, 280, 43, 309, 311, 322, 345,
        213, 670)


    private val listaId = listOf(70, 655, 485, 215, 201, 435, 423, 620, 489,
        10,370, 263, 280, 43, 52, 298, 309, 311, 322, 345, 655, 213,
        670, 538, 550)


    init {
        //getSuperHeroes()
        getSuperHeroesDC()
        getSuperHeroesMarvel()

    }


    /**
     * obtiene 4 cartas para el jugador que las selecciona aleatoriamente
     */
    fun getSuperHeroes(){
        //iniciamos una corrutina
        lista.clear()
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
                        _superHeroesDC.value = lista
                    }
                }catch (e:IOException){
                    Log.d("getSuperHeroes: ", e.message.toString())
                }
            }
        }
    }


    /**
     * obtiene superHeroes de DC
     */
    fun getSuperHeroesDC(){
        //iniciamos una corrutina
        lista.clear()
        for (i in listaIdDc.indices) {
            viewModelScope.launch {
                try {
                    val superHeroId = SuperHeroApi.retrofitService.getSuperHeroById(listaIdDc[i].toString())

                    val gson = Gson()
                    val superheroResponse = gson.fromJson(superHeroId, SuperHero::class.java)
                    if(superheroResponse.response=="success"){
                        lista.add(superheroResponse)
                        _superHeroesDC.value = lista
                    }
                }catch (e:IOException){
                    Log.d("getSuperHeroes: ", e.message.toString())
                }
            }
        }
    }


    fun getSuperHeroesMarvel(){
        //iniciamos una corrutina
        lista.clear()
        for (i in listaIdDc.indices) {
            viewModelScope.launch {
                try {
                    val superHeroId = SuperHeroApi.retrofitService.getSuperHeroById(listaIdMarvel[i].toString())

                    val gson = Gson()
                    val superheroResponse = gson.fromJson(superHeroId, SuperHero::class.java)
                    if(superheroResponse.response=="success"){
                        lista.add(superheroResponse)
                        _superHeroesDC.value = lista
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

    /**
     * función sin implementar que va a permitir poner a pelear a dos superheores
     */
    fun combatir(superHero: SuperHero, superHero2:SuperHero){
        val powerStats1 = superHero.powerStats
        val powerStats2 = superHero2.powerStats

        powerStats1.durability = (powerStats2.strength.toInt() - powerStats1.durability.toInt()).toString()
        powerStats2.durability = (powerStats1.strength.toInt() - powerStats2.durability.toInt()).toString()

    }

//COMIENZA LA PARTE ONLINE

    /**
     * Guarda el superHeroe en la base de datos
     */
    fun saveSuperHero(onSuccess:() -> Unit){
        val email = auth.currentUser?.email

        viewModelScope.launch (Dispatchers.IO){
            try {
                val newSuperHero = hashMapOf(
                    "id" to actualSuperHero.id,
                    "name" to actualSuperHero.name,
                    "powerstats" to actualSuperHero.powerStats,
                    "image" to actualSuperHero.image.url,
                    "emailUser" to email.toString()
                )
                firestore.collection("SuperHeroes")
                    .add(newSuperHero)
                    .addOnSuccessListener {
                        onSuccess()
                        Log.d("Error save","Se guardó el superHeroe")
                    }.addOnFailureListener{
                        Log.d("Save error","Error al guardar")
                    }
            }catch (e:Exception){
                Log.d("Error al guardar nota","Error al guardar SuperHeroe")
            }
        }
    }

    /**
     * muestra los superHeroes guardados
     */

    fun fetchSuperHeroes(){
        val email = auth.currentUser?.email

        firestore.collection("SuperHeroes")
            .whereEqualTo("emailUser",email.toString())
            .addSnapshotListener{querySnapshot, error->
                if(error != null){
                    return@addSnapshotListener
                }
                val superHeroes = mutableListOf<SuperHeroState>()
                if(querySnapshot != null){
                    for(superHero in querySnapshot){
                        val cardSuperHero = superHero.toObject(SuperHeroState::class.java).copy()
                        superHeroes.add(cardSuperHero)
                    }
                }
                _superHeroDeckPlayer.value = superHeroes
            }
    }


}



