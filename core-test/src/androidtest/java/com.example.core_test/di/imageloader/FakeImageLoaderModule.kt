package com.example.core_test.di.imageloader

import com.example.core.di.imageloader.ImageLoader
import com.example.core.di.imageloader.ImageLoaderModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ImageLoaderModule::class]
)
internal abstract class FakeImageLoaderModule {

    @Binds
    abstract fun bindFakeImageLoader(fakeImageLoader: FakeImageLoader): ImageLoader
}