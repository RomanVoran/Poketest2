package com.roman.poketest2.domain

sealed class Response {
    data class Success(val data: PokemonRemote) : Response()
    object Loading : Response()
    data class Error(val errorMessage: String) : Response()
}