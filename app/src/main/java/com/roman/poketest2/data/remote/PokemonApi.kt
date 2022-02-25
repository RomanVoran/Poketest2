package com.roman.poketest2.data.remote

import com.roman.poketest2.domain.PokemonRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("pokemon/{id}")
    suspend fun fetchPokemon(@Path("id") id: Int): PokemonRemote
}