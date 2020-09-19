package com.example.hilt_testing_experimentation.di.analytics

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AnalyticsModule {
    @Binds
    abstract fun provideAnalytics(analyticsImpl: AnalyticsImpl): Analytics
}