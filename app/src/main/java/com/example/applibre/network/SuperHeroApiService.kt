package com.example.applibre.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL =
    "https://www.superheroapi.com/api.php/934555588123234/"


val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface SuperHeroApiService{

    /**
     * Obtiene todo del personaje
     */
    @GET("{id}")
    suspend fun getSuperHeroById(@Path("id") id: String): String


    /**
     * @return devuelve el id del personaje
     */
    @GET("search/{name}")
    suspend fun getSuperHeroByName(@Path("name") name: String): String
}

