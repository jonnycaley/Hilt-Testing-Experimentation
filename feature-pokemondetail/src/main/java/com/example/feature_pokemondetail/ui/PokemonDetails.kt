package com.example.feature_pokemondetail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.DetailedPokemon
import com.example.core.domain.Stat

@Composable
fun PokemonDetails(pokemon: DetailedPokemon) {
    LazyColumn {
        items(pokemon.stats.chunked(2)) { stats ->
            PokemonStatRow(stats)
        }
    }
}

@Composable
fun PokemonStatRow(stats: List<Stat>) {
    Row(
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        val modifierWithWeight = Modifier.weight((1.0/stats.size).toFloat())
        stats.forEach {
            PokemonStat(modifierWithWeight, it)
        }
    }
}

@Composable
fun PokemonStat(modifier: Modifier, stat: Stat) {
    Box(modifier = modifier) {
        Surface(
//            color = MaterialTheme.colors.primary, // TODO: elevation does not appear if color is present, logged a bug for it here https://issuetracker.google.com/issues/183210086
            modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.Center),
            shape = RoundedCornerShape(25),
            elevation = 8.dp // TODO: does not work yet
        ) {
            // because the surface color is set to primary the text color will
            // default to onPrimary
            Text(
                text = "${stat.name}: ${stat.baseStat}".toUpperCase(),
                modifier = Modifier
                    .padding(10.dp),
                color = Color.Red,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPokemonStatRow() {
    PokemonStatRow(
        stats = listOf(Stat(
            name = "attack",
            baseStat = 99
        ), Stat(
            name = "defence",
            baseStat = 99
        ))
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonStat() {
    PokemonStat(
        modifier = Modifier,
        stat = Stat(
            name = "attack",
            baseStat = 99
        )
    )
}