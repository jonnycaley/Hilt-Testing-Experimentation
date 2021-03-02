package com.example.feature_pokemondetail

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.example.core.di.imageloader.ImageLoader
import com.example.core.domain.DetailedPokemon
import com.example.core.viewBinding
import com.example.feature_pokemondetail.databinding.ActivityPokemonDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    private val binding by viewBinding(ActivityPokemonDetailBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        /**
         * delay loading of the activity until we say it is ok to using
         * supportStartPostponedEnterTransition(), which will be called
         * when the image has been loaded or has failed to load
         */
        supportPostponeEnterTransition()
        val extras = intent.extras
        val pokemon: DetailedPokemon = extras?.getParcelable(EXTRA_POKEMON) ?: DetailedPokemon()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName = extras?.getString(EXTRA_IMAGE_TRANSITION_NAME)
            val textTransitionName = extras?.getString(EXTRA_TEXT_TRANSITION_NAME)
            binding.imageView.transitionName = imageTransitionName
            binding.textView.transitionName = textTransitionName
        }

        imageLoader.loadImage(
            this,
            pokemon.imageUrl,
            R.drawable.placeholder,
            binding.imageView,
            { supportStartPostponedEnterTransition() },
            { supportStartPostponedEnterTransition() }
        )
        binding.textView.text = pokemon.name
        binding.composeView.setContent {
            pokemon(pokemon = pokemon)
        }
    }

    @Composable
    fun pokemon(pokemon: DetailedPokemon) {
        pokemon.stats.forEach {
            Text("${it.name} ${it.baseStat}")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        /**
         * There is some bug that causes jetpack compose items
         * to blink when the transition back to the previous
         * screen has finished. This is a quick fix. I opened up
         * an issue here: https://issuetracker.google.com/issues/180643344
         */
        Handler(Looper.getMainLooper()).postDelayed({
            binding.composeView.isVisible = false
        }, 200)
    }

    companion object {

        const val EXTRA_POKEMON = "EXTRA_POKEMON"
        const val EXTRA_IMAGE_TRANSITION_NAME = "EXTRA_IMAGE_TRANSITION_NAME"
        const val EXTRA_TEXT_TRANSITION_NAME = "EXTRA_TEXT_TRANSITION_NAME"

        fun start(
            activity: Activity,
            pokemon: DetailedPokemon,
            imageView: ImageView,
            textView: TextView
        ) {
            val imageTransitionName = "${pokemon.name}-image"
            val textTransitionName = "${pokemon.name}-text"
            ViewCompat.setTransitionName(textView, textTransitionName)
            ViewCompat.setTransitionName(imageView, imageTransitionName)

            val intent = Intent(activity, PokemonDetailActivity::class.java)
            intent.putExtra(EXTRA_POKEMON, pokemon)
            intent.putExtra(EXTRA_IMAGE_TRANSITION_NAME, imageTransitionName)
            intent.putExtra(EXTRA_TEXT_TRANSITION_NAME, textTransitionName)
            /**
             * We create the animation between the two activities using the first activity image
             * and the shared transion name between the image views
             */
            val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                Pair(imageView, imageTransitionName),
                Pair(textView, textTransitionName)
            )
            activity.startActivity(intent, options.toBundle())
        }
    }
}