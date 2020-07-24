package com.example.hilt_testing_experimentation

import android.app.Application
import com.example.hilt_testing_experimentation.data.PokeApiService
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Application : Application() {

    @Inject
    lateinit var service: PokeApiService
}