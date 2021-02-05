package com.example.core.di.imageloader

import com.example.core.di.imageloader.ImageLoader
import com.example.core.di.imageloader.ImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
internal abstract class ImageLoaderModule {

    @Binds
    abstract fun bindImageLoader(imageLoaderImpl: ImageLoaderImpl): ImageLoader
}