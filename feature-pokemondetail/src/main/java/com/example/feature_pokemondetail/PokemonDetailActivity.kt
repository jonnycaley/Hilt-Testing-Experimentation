package com.example.feature_pokemondetail

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.example.core.di.analytics.Analytics
import com.example.core.di.imageloader.ImageLoader
import com.example.core.domain.DetailedPokemon
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var analytics: Analytics

    @Inject
    lateinit var imageLoader: ImageLoader

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

        pokemon?.imageUrl?.let {
            imageLoader.loadImage(
                this,
                it,
                R.drawable.placeholder,
                image_view,
                { supportStartPostponedEnterTransition() },
                { supportStartPostponedEnterTransition() }
            )
        }
    }

    companion object {

        const val EXTRA_POKEMON = "EXTRA_POKEMON"
        const val EXTRA_TRANSITION_NAME = "EXTRA_TRANSITION_NAME"

        fun start(
            activity: Activity,
            pokemon: DetailedPokemon,
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