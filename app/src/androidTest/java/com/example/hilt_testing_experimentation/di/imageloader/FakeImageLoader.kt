package com.example.hilt_testing_experimentation.di.imageloader

import android.content.Context
import android.widget.ImageView

class FakeImageLoader: ImageLoader {

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