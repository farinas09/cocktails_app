package com.farinas.cocktailsapp.application.injection

import com.farinas.cocktailsapp.domain.CocktailRepository
import com.farinas.cocktailsapp.domain.CocktailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Erick Fariñas on 17/09/2021.
 */
@ExperimentalCoroutinesApi
@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    abstract fun dataSource(impl: CocktailRepositoryImpl): CocktailRepository
}