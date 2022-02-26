package com.roman.poketest2.presentation.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.roman.poketest2.databinding.ItemPokemonListBinding
import com.roman.poketest2.domain.PokemonUi

class PokemonListViewHolder(private val binding: ItemPokemonListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pokeData: PokemonUi) = with(binding) {
        binding.pokemonName.text = pokeData.name
        binding.pokemonId.text = pokeData.id.toString()
    }
}