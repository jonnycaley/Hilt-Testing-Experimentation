package com.example.feature_pokemonlist.domain.detailedpokemon

import com.example.core.data.model.detailedpokemondto.StatDto
import com.example.core.domain.Stat
import javax.inject.Inject

class StatMapper @Inject constructor() {
    fun map(dto: StatDto): Stat {
        return Stat(
            baseStat = dto.baseStat,
            name = dto.stat.name
        )
    }
}