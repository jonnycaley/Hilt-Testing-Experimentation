package com.example.core.di.analytics

interface Analytics {
    fun logScreenView(screenView: String)
    fun logImageView(imageView: String)
}