package com.roman.poketest2.data

import com.roman.poketest2.data.local.PokemonDao
import com.roman.poketest2.data.remote.NetworkService
import com.roman.poketest2.domain.Response
import com.roman.poketest2.utils.toLocal
import com.roman.poketest2.utils.toUi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val COUNT = 5

class Repository @Inject constructor(
    private val localRepository: PokemonDao,
    private val remoteRepository: NetworkService
) {

    private suspend fun getRemotePokemonList(offset: Int, count: Int) =
        remoteRepository.fetchPokemonList(offset, count)

    suspend fun getLocalPokeList() = localRepository.getAllPokemons().map { it.toUi() }

    suspend fun deleteAll() = localRepository.removePokemons(localRepository.getAllPokemons())

    suspend fun getPokemons(lastId: Int) = flow {
        val localPokemonList = getLocalPokeList()
        if (localPokemonList.find { localList -> localList.id > lastId } == null) {
            emit(Response.Loading)
            try {
                val remoteData = getRemotePokemonList(lastId + 1, COUNT).map { it.toUi() }
                localRepository.addPokemons(remoteData.map { it.toLocal() })
                emit(Response.Success(remoteData))
            } catch (e: Exception) {
                val errorMessage = e.localizedMessage ?: e.message ?: "Loading error!"
                emit(Response.Error(errorMessage))
            }
        } else {
            emit(Response.Success(localPokemonList))
        }
    }

}