package com.example.feature_pokemondetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.core.domain.detailedpokemon.DetailedPokemon

class PokemonDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
    }

    companion object {
        fun start(pokemon: DetailedPokemon) {
            Log.i("PokemonDetail","Starting activity with pokemon : ${pokemon.name}")
        }
    }
}