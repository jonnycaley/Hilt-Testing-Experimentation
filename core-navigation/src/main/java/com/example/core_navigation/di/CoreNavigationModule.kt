package com.example.core_navigation.di

import com.example.core_navigation.feature_pokemonlist.PokemonListNavigatorImpl
import com.example.feature_pokemonlist.navigation.PokemonListNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class CoreNavigationModule {

    @Binds
    abstract fun bindsPokemonListNavigation(pokemonListNavigatorImpl: PokemonListNavigatorImpl): PokemonListNavigator

}