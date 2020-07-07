package com.example.hilt_testing_experimentation.di.schedulers

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
    fun provideSchedulers(): com.example.hilt_testing_experimentation.di.schedulers.Schedulers {
        return Schedulers(
            Schedulers.io(),
            Schedulers.computation(),
            AndroidSchedulers.mainThread()
        )
    }
}