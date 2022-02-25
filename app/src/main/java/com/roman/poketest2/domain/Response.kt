package com.roman.poketest2.domain

sealed class Response {
    data class Success(val data: List<PokemonUi>) : Response()
    object Loading : Response()
    data class Error(val errorMessage: String) : Response()
}