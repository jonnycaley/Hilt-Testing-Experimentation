package com.example.hilt_testing_experimentation.di.analytics

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