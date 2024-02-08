package com.example.applibre.network

import com.example.applibre.data.SuperHero
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL =
    "https://superheroapi.com/api/934555588123234"

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface SuperHeroApiService{

    /**
     * Obtiene el id de los superhéroes
     */
    @GET("/70")
    suspend fun getSuperHero(): String


    @GET("/search/{type}")
    suspend fun getSuperHero(@Path("type") type: String): String
}