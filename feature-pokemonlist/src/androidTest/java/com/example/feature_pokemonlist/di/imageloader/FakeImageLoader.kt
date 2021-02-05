package com.example.feature_pokemonlist.di.imageloader

import android.content.Context
import android.widget.ImageView
import com.example.core.di.imageloader.ImageLoader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeImageLoader @Inject constructor(): ImageLoader {

    var loadSuccessfully = true

    override fun loadImage(
        context: Context,
        loadable: Any,
        placeholder: Int,
        imageView: ImageView,
        onLoadFailed: () -> Unit,
        onResourceReady: () -> Unit
    ) {
        if (loadSuccessfully)
            onResourceReady.invoke()
        else
            onLoadFailed.invoke()
    }
}