package com.roman.poketest2.data

import com.roman.poketest2.data.local.PokemonDao
import com.roman.poketest2.data.remote.NetworkService
import com.roman.poketest2.domain.Response
import com.roman.poketest2.utils.toUi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val COUNT = 5

class Repository @Inject constructor(
    private val localRepository: PokemonDao,
    private val remoteRepository: NetworkService
) {

    private suspend fun fetchPokemon(offset: Int, count: Int) =
        remoteRepository.fetchPokemonList(offset, count)

    private suspend fun getLocalPokeList() = localRepository.getAllPokemons().map { it.toUi() }

    suspend fun getPokemons(lastId: Int) = flow {
        val localData = getLocalPokeList()
        if (localData.find { it.id < lastId } == null) {
            emit(Response.Loading)
            try {
                emit(Response.Success(fetchPokemon(lastId + 1, COUNT).map { it.toUi() }))
            } catch (e: Exception) {
                val errorMessage = e.localizedMessage ?: e.message ?: "Loading error!"
                emit(Response.Error(errorMessage))
            }
        }
    }
}