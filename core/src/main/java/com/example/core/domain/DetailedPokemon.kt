package com.example.core.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailedPokemon(
    val name: String?,
    val imageUrl: String?
) : Parcelable