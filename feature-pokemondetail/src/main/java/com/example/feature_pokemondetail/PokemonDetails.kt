package com.example.feature_pokemondetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.DetailedPokemon

@Composable
fun PokemonDetails(pokemon: DetailedPokemon) {
    Column {
        LazyColumn {
            items(items = pokemon.stats) {
                Row(
                    modifier = Modifier.apply {
                    }
                ) {
                    Box(
                        modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(Color.Red)
                    )
                    Text("${it.name} ${it.baseStat}")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPokemonDetails() {
    PokemonDetails(pokemon = DetailedPokemon())
}