package com.example.feature_pokemonlist.di.schedulers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@Module
@InstallIn(ApplicationComponent::class)
object SchedulersModule {

    @Provides
    fun provideSchedulers(): AppSchedulers {
        return AppSchedulers(
            Schedulers.io(),
            Schedulers.computation(),
            AndroidSchedulers.mainThread()
        )
    }
}