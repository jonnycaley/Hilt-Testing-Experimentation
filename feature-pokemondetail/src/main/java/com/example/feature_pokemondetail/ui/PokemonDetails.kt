package com.example.feature_pokemondetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.domain.DetailedPokemon
import com.example.core.domain.Stat

@Composable
fun PokemonDetails(pokemon: DetailedPokemon) {
    LazyColumn {
        items(pokemon.stats.zipWithNext()) { statPair ->
            PokemonStatRow(statPair)
        }
    }
}

@Composable
fun PokemonStatRow(statPair: Pair<Stat, Stat>) {
    Row(
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        val modifierWithWeight = Modifier.weight(0.5F)
        PokemonStat(modifierWithWeight, statPair.first)
        PokemonStat(modifierWithWeight, statPair.second)
    }
}

@Composable
fun PokemonStat(modifier: Modifier, stat: Stat) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .align(Alignment.Center)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.Red)
        ) {
            Text("${stat.name}: ${stat.baseStat}",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp),
                color = Color.White
            )
        }
    }
}