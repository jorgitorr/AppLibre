package com.example.applibre.network

import retrofit2.Retrofit

object SuperHeroApi {

   val retrofitService:SuperHeroApiService by lazy {
       retrofit.create(SuperHeroApiService::class.java)
   }
}