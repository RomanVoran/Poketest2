package com.roman.poketest2.data.remote

import com.roman.poketest2.domain.PokemonRemote
import javax.inject.Inject

class NetworkService @Inject constructor(private val pokeApi: PokemonApi) {

    suspend fun fetchPokemonList(offset: Int, count: Int): MutableList<PokemonRemote> {
        val remoteList = mutableListOf<PokemonRemote>()
        for (i in (offset until offset + count)) {
            remoteList.add(fetchPokemon(i))
        }
        return remoteList
    }

    private suspend fun fetchPokemon(id: Int) = pokeApi.fetchPokemon(id)

}