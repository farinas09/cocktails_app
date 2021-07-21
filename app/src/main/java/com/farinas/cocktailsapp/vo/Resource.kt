package com.farinas.cocktailsapp.vo

/**
 * Created by Erick Fariñas on 21/07/2021.
 */
sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val exception: Exception) : Resource<T>()
}