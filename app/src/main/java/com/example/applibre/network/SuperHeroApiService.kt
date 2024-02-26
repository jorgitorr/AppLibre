package com.example.applibre.network

import com.example.applibre.util.Constants.Companion.API_KEY
import com.example.applibre.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @param URL es la url final con la url base y la key
 * @param retrofit inicia el servicio de retrofit para acceder a la api
 */
const val URL =
    "$BASE_URL$API_KEY/"


val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(URL)
    .build()




interface SuperHeroApiService{

    /**
     * Obtiene todo del superheroe a traves de un id
     */
    @GET("{id}")
    suspend fun getSuperHeroById(@Path("id") id: String): String



    /**
     * @return devuelve el id del personaje a través de un nombre
     */
    @GET("search/{name}")
    suspend fun getSuperHeroByName(@Path("name") name: String): String
}

