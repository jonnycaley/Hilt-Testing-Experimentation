package com.example.feature_pokemondetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.core.di.analytics.Analytics
import com.example.core.domain.DetailedPokemon
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var analytics: Analytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
    }

    companion object {
        fun start(context: Context, pokemon: DetailedPokemon) {
            val intent = Intent(context, PokemonDetailActivity::class.java)
            context.startActivity(intent)
        }
    }
}