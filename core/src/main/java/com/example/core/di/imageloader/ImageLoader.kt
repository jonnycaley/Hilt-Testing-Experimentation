package com.example.core.di.imageloader

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoader {
    fun loadImage(
        context: Context,
        loadable: Any,
        @DrawableRes placeholder: Int,
        imageView: ImageView,
        onLoadFailed: () -> Unit = { },
        onResourceReady: () -> Unit = { }
    )
}