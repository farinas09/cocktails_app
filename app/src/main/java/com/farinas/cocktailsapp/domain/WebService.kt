package com.farinas.cocktailsapp.domain

import com.farinas.cocktailsapp.data.model.CocktailsList
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Erick Fariñas on 21/07/2021.
 */
interface WebService {
    @GET("search.php")
    suspend fun getCocktailByName(@Query("s") cocktailName: String): CocktailsList
}