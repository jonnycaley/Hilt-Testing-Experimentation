package com.example.feature_pokemonlist.di.analytics

interface Analytics {
    fun logScreenView(screenView: String)
    fun logImageView(imageView: String)
}