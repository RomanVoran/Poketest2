package com.roman.poketest2.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman.poketest2.data.Repository
import com.roman.poketest2.domain.PokemonUi
import com.roman.poketest2.domain.Response
import com.roman.poketest2.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    private val pokemonList = mutableListOf<PokemonUi>()

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> get() = _showLoading

    private val _eventErrorMessage = SingleLiveEvent<String>()
    val eventErrorMessage: LiveData<String> get() = _eventErrorMessage

    private val _updatePokeList = SingleLiveEvent<List<PokemonUi>>()
    val updatePokeList: LiveData<List<PokemonUi>> get() = _updatePokeList


    fun fetchPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPokemons(pokemonList.size).collect { response ->
                when (response) {
                    is Response.Error -> {
                        _showLoading.postValue(false)
                        _eventErrorMessage.postValue(response.errorMessage)
                    }
                    Response.Loading -> {
                        _showLoading.postValue(true)
                    }
                    is Response.Success -> {
                        _showLoading.postValue(false)
                        pokemonList.addAll(response.data)
                        _updatePokeList.postValue(pokemonList)
                    }
                }
            }
        }

    }


}