package com.roman.poketest2.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonLocal(
    @PrimaryKey val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val health: Int,
    val damage: Int,
    val defence: Int,
    val type: String,
    val imageUrl: String
)