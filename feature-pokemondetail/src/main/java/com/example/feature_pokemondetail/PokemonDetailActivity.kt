package com.example.feature_pokemondetail

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.core.di.analytics.Analytics
import com.example.core.domain.DetailedPokemon
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var analytics: Analytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        supportPostponeEnterTransition()
        analytics.logScreenView("PokemonDetailActivity")
        val extras = intent.extras
        val pokemon: DetailedPokemon? = extras!!.getParcelable(EXTRA_POKEMON)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName = extras.getString(EXTRA_TRANSITION_NAME)
            image_view.transitionName = imageTransitionName
        }
        Glide
            .with(this)
            .load(pokemon?.imageUrl)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    supportStartPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    supportStartPostponedEnterTransition()
                    return true
                }
            })
            .into(image_view)
    }

    companion object {

        const val EXTRA_POKEMON = "EXTRA_POKEMON"
        const val EXTRA_TRANSITION_NAME = "EXTRA_TRANSITION_NAME"

        fun start(
            activity: Activity,
            pokemon: DetailedPokemon,
            adapterPosition: Int,
            imageView: ImageView
        ) {
            val intent = Intent(activity, PokemonDetailActivity::class.java)
            intent.putExtra(EXTRA_POKEMON, pokemon)
            intent.putExtra(EXTRA_TRANSITION_NAME, ViewCompat.getTransitionName(imageView))
            val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                imageView,
                ViewCompat.getTransitionName(imageView) ?: ""
            )
            activity.startActivity(intent, options.toBundle())
        }
    }
}