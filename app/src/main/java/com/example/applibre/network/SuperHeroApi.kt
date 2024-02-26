package com.example.applibre.network


object SuperHeroApi {
   val retrofitService:SuperHeroApiService by lazy {
       retrofit.create(SuperHeroApiService::class.java)
   }
}