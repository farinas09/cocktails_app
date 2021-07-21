package com.farinas.cocktailsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.farinas.cocktailsapp.data.model.Drink
import com.farinas.cocktailsapp.domain.Repository
import com.farinas.cocktailsapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

/**
 * Created by Erick Fariñas on 21/07/2021.
 */
class MainViewModel(private val repository: Repository):ViewModel() {
    val fetchCocktailsList = liveData<Resource<List<Drink>>>(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repository.getCocktailsList())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}