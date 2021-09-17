package com.farinas.cocktailsapp.ui.viewmodel

import androidx.lifecycle.*
import com.farinas.cocktailsapp.data.model.Cocktail
import com.farinas.cocktailsapp.data.model.CocktailEntity
import com.farinas.cocktailsapp.domain.Repository
import com.farinas.cocktailsapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by Erick Fariñas on 21/07/2021.
 */
class MainViewModel(private val repository: Repository):ViewModel() {

    private val cocktailsData = MutableLiveData<String>()

    fun setCocktail(cocktailName: String) {
        cocktailsData.value = cocktailName
    }

    init {
        setCocktail("margarita")
    }

    val fetchCocktailsList = cocktailsData.distinctUntilChanged().switchMap { cocktailName ->
        liveData<Resource<List<Cocktail>>>(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repository.getCocktailsList(cocktailName))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    fun saveFavorite(cocktail: CocktailEntity){
        viewModelScope.launch {
            repository.insertFavoriteCocktail(cocktail)
        }
    }

    fun getFavoritesCocktails() = liveData<Resource<List<Cocktail>>>(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repository.getFavoriteCocktails())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
    fun deleteCocktail(cocktail: Cocktail) {
        viewModelScope.launch {
            repository.deleteCocktail(cocktail)
        }
    }
}