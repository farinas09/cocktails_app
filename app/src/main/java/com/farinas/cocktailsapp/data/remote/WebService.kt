package com.farinas.cocktailsapp.data.remote

import com.farinas.cocktailsapp.data.model.CocktailsList
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Erick Fariñas on 17/09/2021.
 */
interface WebService {
    @GET("search.php")
    suspend fun getCocktailByName(@Query("s") cocktailName: String): CocktailsList?
}