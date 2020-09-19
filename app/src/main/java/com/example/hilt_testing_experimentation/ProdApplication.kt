package com.example.hilt_testing_experimentation

import android.app.Application
import com.example.hilt_testing_experimentation.di.analytics.Analytics
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ProdApplication: Application() {
    @Inject
    lateinit var analytics: Analytics
    override fun onCreate() {
        super.onCreate()

        analytics.logScreenView("Application")
    }
}