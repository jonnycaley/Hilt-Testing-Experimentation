package com.example.hilt_testing_experimentation.di.analytics

import com.example.feature_pokemonlist.di.analytics.Analytics

class FakeAnalytics : Analytics {

    val screenViews = mutableListOf<String>()
    val imageViews = mutableListOf<String>()

    override fun logScreenView(screenView: String) {
        screenViews.add(screenView)
    }

    override fun logImageView(imageView: String) {
        imageViews.add(imageView)
    }
}