package com.farinas.cocktailsapp.domain

import com.farinas.cocktailsapp.data.DataSource
import com.farinas.cocktailsapp.data.model.Cocktail
import com.farinas.cocktailsapp.data.model.CocktailEntity
import com.farinas.cocktailsapp.vo.Resource

/**
 * Created by Erick Fariñas on 21/07/2021.
 */
class RepositoryImpl(private val dataSource: DataSource): Repository {
    override suspend fun getCocktailsList(cocktailName: String): Resource<List<Cocktail>> {
        return dataSource.getCocktailByName(cocktailName)
    }

    override suspend fun getFavoriteCocktails(): Resource<List<Cocktail>> {
        return dataSource.getFavoriteCocktails()
    }

    override suspend fun insertFavoriteCocktail(cocktail: CocktailEntity) {
        dataSource.insertFavoriteCocktail(cocktail)
    }

    override suspend fun deleteCocktail(cocktail: Cocktail) {
        dataSource.deleteCocktail(cocktail)
    }
}