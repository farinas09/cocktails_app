package com.farinas.cocktailsapp.data

import com.farinas.cocktailsapp.AppDatabase
import com.farinas.cocktailsapp.data.model.Cocktail
import com.farinas.cocktailsapp.data.model.CocktailEntity
import com.farinas.cocktailsapp.vo.Resource
import com.farinas.cocktailsapp.vo.RetrofitClient

/**
 * Created by Erick Fariñas on 21/07/2021.
 */
class DataSource(private val appDatabase: AppDatabase) {

    suspend fun getCocktailByName(cocktailName: String): Resource<List<Cocktail>>{
        return Resource.Success(RetrofitClient.webService.getCocktailByName(cocktailName).cocktailList)
    }
    suspend fun getFavoriteCocktails(): Resource<List<CocktailEntity>>{
        return Resource.Success(appDatabase.cocktailsDao().getAllFavoriteCocktails())
    }
    suspend fun insertFavoriteCocktail(cocktail: CocktailEntity){
        appDatabase.cocktailsDao().insertFavorite(cocktail)
    }
}