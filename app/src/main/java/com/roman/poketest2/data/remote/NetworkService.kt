package com.roman.poketest2.data.remote

import javax.inject.Inject

class NetworkService @Inject constructor(private val pokeApi: PokemonApi) {

    suspend fun fetchPokemon(id: Int) = pokeApi.fetchPokemon()

}