package com.example.applibre.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object SuperHeroApi {

   val retrofitService:SuperHeroApiService by lazy {
       retrofit.create(SuperHeroApiService::class.java)
   }
}