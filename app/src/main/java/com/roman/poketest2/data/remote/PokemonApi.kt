package com.roman.poketest2.data.remote

import com.roman.poketest2.domain.PokemonRemote
import retrofit2.http.GET

interface PokemonApi {
    @GET("pokemon/4")
    suspend fun fetchPokemon(): PokemonRemote
}