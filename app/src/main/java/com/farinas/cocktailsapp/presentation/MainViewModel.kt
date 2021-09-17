package com.farinas.cocktailsapp.presentation

import androidx.lifecycle.*
import com.farinas.cocktailsapp.application.ToastHelper
import com.farinas.cocktailsapp.core.Resource
import com.farinas.cocktailsapp.data.model.Cocktail
import com.farinas.cocktailsapp.domain.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Erick Fariñas on 21/07/2021.
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CocktailRepository,
    private val toastHelper: ToastHelper,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val currentCocktailName = savedStateHandle.getLiveData<String>("cocktailName", "margarita")

    fun setCocktail(cocktailName: String) {
        currentCocktailName.value = cocktailName
    }

    val fetchCocktailsList = currentCocktailName.distinctUntilChanged().switchMap { cocktailName ->
        liveData<Resource<List<Cocktail>>>(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                repository.getCocktailByName(cocktailName).collect {
                    emit(it)
                }
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    fun saveOrDeleteFavoriteCocktail(cocktail: Cocktail) {
        viewModelScope.launch {
            if (repository.isCocktailFavorite(cocktail)) {
                repository.deleteFavoriteCocktail(cocktail)
                toastHelper.sendToast("Cocktail deleted from favorites")
            } else {
                repository.saveFavoriteCocktail(cocktail)
                toastHelper.sendToast("Cocktail saved to favorites")
            }
        }
    }

    fun getFavoritesCocktails() = liveData<Resource<List<Cocktail>>>(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emitSource(repository.getFavoritesCocktails().map { Resource.Success(it) })
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
    fun deleteFavoriteCocktail(cocktail: Cocktail) {
        viewModelScope.launch {
            repository.deleteFavoriteCocktail(cocktail)
            toastHelper.sendToast("Cocktail deleted from favorites")
        }
    }

    suspend fun isCocktailFavorite(cocktail: Cocktail): Boolean =
        repository.isCocktailFavorite(cocktail)
}