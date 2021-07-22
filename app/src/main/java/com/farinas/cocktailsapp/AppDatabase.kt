package com.farinas.cocktailsapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.farinas.cocktailsapp.data.model.CocktailEntity
import com.farinas.cocktailsapp.domain.CocktailsDao

/**
 * Created by Erick Fariñas on 22/07/2021.
 */

@Database(entities = arrayOf(CocktailEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cocktailsDao(): CocktailsDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "CocktailDB"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstence() {
            INSTANCE = null
        }
    }
}