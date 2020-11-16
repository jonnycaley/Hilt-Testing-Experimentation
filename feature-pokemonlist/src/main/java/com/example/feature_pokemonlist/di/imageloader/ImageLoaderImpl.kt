package com.example.feature_pokemonlist.di.imageloader

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import javax.inject.Inject

class ImageLoaderImpl @Inject constructor() : ImageLoader {

    override fun loadImage(
        context: Context,
        loadable: Any,
        @DrawableRes placeholder: Int,
        imageView: ImageView,
        onLoadFailed: () -> Unit,
        onResourceReady: () -> Unit
    ) {
        Glide
            .with(context)
            .load(loadable)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    onLoadFailed.invoke()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    onResourceReady.invoke()
                    return false
                }
            })
            .error(placeholder)
            .into(imageView)
    }
}