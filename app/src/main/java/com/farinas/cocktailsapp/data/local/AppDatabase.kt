package com.farinas.cocktailsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.farinas.cocktailsapp.data.model.CocktailEntity
import com.farinas.cocktailsapp.data.model.FavoritesEntity

/**
 * Created by Erick Fariñas on 22/07/2021.
 */

@Database(entities = [FavoritesEntity::class, CocktailEntity::class],version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao
}