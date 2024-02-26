package com.example.applibre.ui.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applibre.data.model.SuperHero
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
     * @param lista guarda los superheroes en una lista para después pasarlo a la final
     * @param actualSuperHero superHeroe seleccionado actual
     * @param listaId contiene id de los superheroes que podemos tener entre las cartas
     * contiene los id de los superheroes y estos son los que contiene:
     *     ID -> nombre superHeroe
     *     70->batman, 655->superman, 485->naruto, 215->Deathlok, 201->daredevil, 435->master chief,
     *     423->magneto, 620->spiderman, 489->nick fury, 10->agent bob, 263->flash, 280->Ghost Rider,
     *     43->Ares, 52->Atom Girl, 298->Green Arrow, 309->Harley Queen, 311->Havok, 322->HellBoy,
     *     345->Fire Man, 213->Death-pool, 670->Toad, 538->Ra's Al Ghu, 550->Red Skull
     * */

    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private val firestore = Firebase.firestore

    private val _superHero  = MutableStateFlow<List<SuperHero>>(emptyList())
    val superHeroDeck: StateFlow<List<SuperHero>> = _superHero

    private val _superHeroDeckPlayer = MutableStateFlow<List<SuperHero>>(emptyList())

    private val lista: MutableList<SuperHero> = mutableListOf()
    private var actualSuperHero by mutableStateOf(SuperHero())
        private set;

    private val listaId = listOf(70, 655, 485, 215, 201, 435, 423, 620, 489,
        10,370, 263, 280, 43, 52, 298, 309, 311, 322, 345, 655, 213,
        670, 538, 550)

    init {
        getSuperHeroes()
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
                        Log.d("Error save","Se guardó la nota")
                    }.addOnFailureListener{
                        Log.d("Save error","Error al guardar")
                    }
            }catch (e:Exception){
                Log.d("Error al guardar nota","Error al guardar nota")
            }
        }
    }

    /**
     * muestra los superHeroes guardados
     */

    fun fetchSuperHeroes():StateFlow<List<SuperHero>>{
        val email = auth.currentUser?.email

        firestore.collection("SuperHeroes")
            .whereEqualTo("emailUser",email.toString())
            .addSnapshotListener{querySnapshot, error->
                if(error != null){
                    return@addSnapshotListener
                }
                val superHeroes = mutableListOf<SuperHero>()
                if(querySnapshot != null){
                    for(superHero in querySnapshot){
                        val cardSuperHero = superHero.toObject(SuperHero::class.java).copy(response = superHero.id)
                        superHeroes.add(cardSuperHero)
                    }
                }
                _superHeroDeckPlayer.value = superHeroes
            }
        return _superHeroDeckPlayer
    }


}



