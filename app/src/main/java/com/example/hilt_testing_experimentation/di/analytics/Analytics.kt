package com.example.hilt_testing_experimentation.di.analytics

interface Analytics {
    fun logScreenView(screenView: String)
    fun logImageView(imageView: String)
}