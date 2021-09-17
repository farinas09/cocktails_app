package com.farinas.cocktailsapp.vo

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Erick Fariñas on 21/07/2021.
 */
object RetrofitClient {
    private const val API_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}