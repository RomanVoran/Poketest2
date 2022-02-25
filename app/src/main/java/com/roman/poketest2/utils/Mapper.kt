package com.roman.poketest2.utils

import com.roman.poketest2.domain.PokemonLocal
import com.roman.poketest2.domain.PokemonRemote
import com.roman.poketest2.domain.PokemonUi

fun PokemonRemote.toUi() = PokemonUi(
    id = id,
    name = name,
    height = height,
    weight = weight,
    health = stats.find { name == "hp" }?.baseStat ?: 0,
    damage = stats.find { name == "attack" }?.baseStat ?: 0,
    defence = stats.find { name == "defence" }?.baseStat ?: 0,
    type = types[0].type.name,
    imageUrl = sprites.other.dreamWorld.frontDefault
)

fun PokemonRemote.toLocal() = PokemonLocal(
    id = id,
    name = name,
    height = height,
    weight = weight,
    health = stats.find { name == "hp" }?.baseStat ?: 0,
    damage = stats.find { name == "attack" }?.baseStat ?: 0,
    defence = stats.find { name == "defence" }?.baseStat ?: 0,
    type = types[0].type.name,
    imageUrl = sprites.other.dreamWorld.frontDefault
)

fun PokemonUi.toLocal() = PokemonLocal(
    id = id,
    name = name,
    height = height,
    weight = weight,
    health = health,
    damage = damage,
    defence = defence,
    type = type,
    imageUrl = imageUrl
)

fun PokemonLocal.toUi() = PokemonUi(
    id = id,
    name = name,
    height = height,
    weight = weight,
    health = health,
    damage = damage,
    defence = defence,
    type = type,
    imageUrl = imageUrl
)
