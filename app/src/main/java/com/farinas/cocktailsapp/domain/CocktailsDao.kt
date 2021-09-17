package com.farinas.cocktailsapp.domain

import androidx.room.*
import com.farinas.cocktailsapp.data.model.CocktailEntity

/**
 * Created by Erick Fariñas on 22/07/2021.
 */
@Dao
interface CocktailsDao {

    @Query("SELECT * FROM cocktailTable")
    suspend fun getAllFavoriteCocktails(): List<CocktailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(cocktail: CocktailEntity)

    @Delete
    suspend fun deleteCocktail(cocktail: CocktailEntity)
}