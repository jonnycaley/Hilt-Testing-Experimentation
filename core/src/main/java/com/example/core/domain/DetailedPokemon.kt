package com.example.core.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailedPokemon(
    val name: String = "",
    val imageUrl: String = "",
    val stats: List<Stat> = emptyList()
) : Parcelable