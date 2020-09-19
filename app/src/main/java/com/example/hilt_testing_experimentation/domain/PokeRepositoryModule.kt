package com.example.hilt_testing_experimentation.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class PokeRepositoryModule {

    @Binds
    abstract fun bindPokeRepository(pokeRepositoryImpl: PokeRepositoryImpl): PokeRepository
}