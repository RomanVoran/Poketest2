package com.roman.poketest2.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonUi(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val health: Int,
    val damage: Int,
    val defence: Int,
    val type: String,
    val imageUrl: String
) : Parcelable