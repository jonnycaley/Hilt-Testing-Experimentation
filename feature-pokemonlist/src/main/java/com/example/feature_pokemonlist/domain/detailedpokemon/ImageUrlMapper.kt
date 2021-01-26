package com.example.feature_pokemonlist.domain.detailedpokemon

import javax.inject.Inject

class ImageUrlMapper @Inject constructor() {
    fun map(url: String?): String {
        val index = url?.split("/".toRegex())?.dropLast(1)?.last()
        return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
    }
}