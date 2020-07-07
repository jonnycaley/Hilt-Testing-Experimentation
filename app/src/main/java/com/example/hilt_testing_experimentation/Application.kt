package com.example.hilt_testing_experimentation

import android.app.Application
import com.example.hilt_testing_experimentation.data.YahooFinanceService
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Application : Application() {

    @Inject
    lateinit var service: YahooFinanceService
}