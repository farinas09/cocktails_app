package com.farinas.cocktailsapp.domain

import androidx.lifecycle.LiveData
import com.farinas.cocktailsapp.core.Resource
import com.farinas.cocktailsapp.data.local.LocalDataSource
import com.farinas.cocktailsapp.data.model.Cocktail
import com.farinas.cocktailsapp.data.model.CocktailEntity
import com.farinas.cocktailsapp.data.model.asCocktailEntity
import com.farinas.cocktailsapp.data.remote.NetworkDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by Erick Fariñas on 21/07/2021.
 */

@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class CocktailRepositoryImpl@Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) : CocktailRepository {
    override suspend fun getCocktailByName(cocktailName: String): Flow<Resource<List<Cocktail>>> =
        callbackFlow {

            offer(getCachedCocktails(cocktailName))

            networkDataSource.getCocktailByName(cocktailName).collect {
                when (it) {
                    is Resource.Success -> {
                        for (cocktail in it.data) {
                            saveCocktail(cocktail.asCocktailEntity())
                        }
                        offer(getCachedCocktails(cocktailName))
                    }
                    is Resource.Failure -> {
                        offer(getCachedCocktails(cocktailName))
                    }
                }
            }
            awaitClose { cancel() }
        }

    override suspend fun saveFavoriteCocktail(cocktail: Cocktail) {
        localDataSource.saveFavoriteCocktail(cocktail)
    }

    override suspend fun isCocktailFavorite(cocktail: Cocktail): Boolean =
        localDataSource.isCocktailFavorite(cocktail)

    override suspend fun saveCocktail(cocktail: CocktailEntity) {
        localDataSource.saveCocktail(cocktail)
    }

    override fun getFavoritesCocktails(): LiveData<List<Cocktail>> {
        return localDataSource.getFavoritesCocktails()
    }

    override suspend fun deleteFavoriteCocktail(cocktail: Cocktail) {
        localDataSource.deleteCocktail(cocktail)
    }

    override suspend fun getCachedCocktails(cocktailName: String): Resource<List<Cocktail>> {
        return localDataSource.getCachedCocktails(cocktailName)
    }

}