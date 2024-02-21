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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import java.io.IOException
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
     * @param listaId contiene id de los superheroes que podemos tener entre las cartas
     * @param lista guarda los superheroes en una lista para después pasarlo a la final
     */
    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private val firestore = Firebase.firestore
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("card")

    private val _superHero  = MutableStateFlow<List<SuperHero>>(emptyList())
    val superHeroDeck: StateFlow<List<SuperHero>> = _superHero

    /**    ID -> NOMBRE SUPERHEROE
     *     70->batman, 655->superman, 485->naruto, 215->Deathlok, 201->daredevil, 435->masterchief,
     *     423->magneto, 620->spiderman, 489->nickfury, 10->agente bob, 263->flash, 280->Ghost Rider,
     *     43->Ares, 52->Atom Girl, 298->Green Arrow, 309->Harley Queen, 311->Havok, 322->HellBoy,
     *     345->Fire Man, 213->Deathpool, 670->Toad, 538->Ra's Al Ghu, 550->Red Skull
     * */

    private val listaId = listOf(70, 655, 485, 215, 201, 435, 423, 620, 489,
        10,370, 263, 280, 43, 52, 298, 309, 311, 322, 345, 655, 213,
        670, 538, 550)

    private val lista: MutableList<SuperHero> = mutableListOf()
    private var actualSuperHero by mutableStateOf(SuperHero())
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

    }


    fun getSuperHeroe(): Flow<List<com.example.applibre.data.model.db.SuperHero>> {
        val idFilter = auth.currentUser?.uid
        val flow = callbackFlow {
            val listener = databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val superHeroes = snapshot.children.mapNotNull {
                        val superHero = snapshot.getValue(com.example.applibre.data.model.db.SuperHero::class.java)
                        snapshot.key?.let{superHero?.copy(key = it)}
                    }
                    trySend(superHeroes.filter { it.id == idFilter })
                }
                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
            awaitClose { databaseReference.removeEventListener(listener) }
        }
        return flow
    }


}



