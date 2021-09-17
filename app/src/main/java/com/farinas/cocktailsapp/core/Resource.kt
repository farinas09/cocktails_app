package com.farinas.cocktailsapp.core

/**
 * Created by Erick Fariñas on 17/09/2021.
 */
sealed class Resource<out T> {
    object Loading: Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val exception: Exception) : Resource<Nothing>()
}