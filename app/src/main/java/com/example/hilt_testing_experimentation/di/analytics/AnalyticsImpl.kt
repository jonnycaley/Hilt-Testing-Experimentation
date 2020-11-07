package com.example.hilt_testing_experimentation.di.analytics

import android.util.Log
import javax.inject.Inject

class AnalyticsImpl @Inject constructor(): Analytics {
    override fun logScreenView(screenView: String) {
        Log.d("Analytics","Logging screen view: $screenView")
    }

    override fun logImageView(imageView: String) {
        Log.d("Analytics","Logging image view: $imageView")
    }
}