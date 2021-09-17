package com.farinas.cocktailsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.farinas.cocktailsapp.data.model.CocktailEntity
import com.farinas.cocktailsapp.data.model.FavoritesEntity

/**
 * Created by Erick Fariñas on 22/07/2021.
 */
@Dao
interface CocktailDao {

    @Query("SELECT * FROM favoritesTable")
    suspend fun getAllFavoriteCocktails(): List<FavoritesEntity>

    @Query("SELECT * FROM favoritesTable")
    fun getAllFavoritesWithChanges(): LiveData<List<FavoritesEntity>>

    @Query("SELECT * FROM cocktailTable WHERE cocktail_nombre LIKE '%' || :cocktailName || '%'") // This Like operator is needed due that the API returns blank spaces in the name
    suspend fun getCocktailsByName(cocktailName: String): List<CocktailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteCocktail(trago: FavoritesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCocktail(cocktail: CocktailEntity)

    @Delete
    suspend fun deleteFavoriteCoktail(favorites: FavoritesEntity)

    @Query("SELECT * FROM favoritesTable WHERE cocktailId = :cocktailId")
    suspend fun getCocktailById(cocktailId: String): FavoritesEntity?
}