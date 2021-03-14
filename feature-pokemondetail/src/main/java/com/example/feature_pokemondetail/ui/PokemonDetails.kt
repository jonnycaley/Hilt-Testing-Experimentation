package com.example.feature_pokemondetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.DetailedPokemon
import com.example.core.domain.Stat

@Composable
fun PokemonDetails(pokemon: DetailedPokemon) {
    Column {
        LazyColumn {
            items(items = pokemon.stats.zipWithNext()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)) {
                    PokemonStatRow(it)
                }
            }
        }
    }
}

@Composable
fun PokemonStatRow(statPair: Pair<Stat, Stat>) {
    PokemonStat(statPair.first)
    PokemonStat(statPair.second)
}

@Composable
fun PokemonStat(stat: Stat) {
    Box(
        modifier = Modifier
            .preferredSize(150.dp, 30.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Red)
    ) {
        Text("${stat.name}: ${stat.baseStat}",
            modifier = Modifier.align(Alignment.Center),
            color = Color.White
        )
    }
}

@Preview
@Composable
fun PreviewPokemonPokemonStat() {
}