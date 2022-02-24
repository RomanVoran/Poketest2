package com.roman.poketest2.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman.poketest2.data.remote.NetworkService
import com.roman.poketest2.domain.PokemonRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(val networkService: NetworkService) : ViewModel() {


    val data = MutableLiveData<PokemonRemote>()


    fun fetchPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(networkService.fetchPokemon(4))
        }
    }


}