package com.farinas.cocktailsapp.domain

import com.farinas.cocktailsapp.data.model.Cocktail
import com.farinas.cocktailsapp.data.model.CocktailEntity
import com.farinas.cocktailsapp.vo.Resource

/**
 * Created by Erick Fariñas on 21/07/2021.
 */
interface Repository {
    suspend fun getCocktailsList(cocktailName: String): Resource<List<Cocktail>>
    suspend fun getFavoriteCocktails(): Resource<List<CocktailEntity>>
    suspend fun insertFavoriteCocktail(cocktail: CocktailEntity)
}