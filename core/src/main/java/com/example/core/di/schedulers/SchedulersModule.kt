package com.example.core.di.schedulers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object SchedulersModule {

    @Singleton
    @Provides
    fun provideSchedulers(): AppSchedulers {
        return AppSchedulers(
            Schedulers.io(),
            Schedulers.computation(),
            AndroidSchedulers.mainThread()
        )
    }
}