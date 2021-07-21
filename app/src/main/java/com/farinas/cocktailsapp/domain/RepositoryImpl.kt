package com.farinas.cocktailsapp.domain

import com.farinas.cocktailsapp.data.DataSource
import com.farinas.cocktailsapp.data.model.Drink
import com.farinas.cocktailsapp.vo.Resource

/**
 * Created by Erick Fariñas on 21/07/2021.
 */
class RepositoryImpl(private val dataSource: DataSource): Repository {
    override fun getCocktailsList(): Resource<List<Drink>> {
        return dataSource.getCocktailsList()
    }
}