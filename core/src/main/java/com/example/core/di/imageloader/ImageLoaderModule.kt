package com.example.core.di.imageloader

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class ImageLoaderModule {

    @Binds
    abstract fun bindImageLoader(imageLoaderImpl: ImageLoaderImpl): ImageLoader
}