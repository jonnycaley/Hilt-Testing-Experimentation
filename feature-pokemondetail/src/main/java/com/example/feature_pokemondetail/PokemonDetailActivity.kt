package com.example.feature_pokemondetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class PokemonDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
    }

    companion object {
        fun start() {
            Log.i("PokemonDetail","Starting activity")
        }
    }
}