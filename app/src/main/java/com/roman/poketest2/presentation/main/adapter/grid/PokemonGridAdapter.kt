package com.roman.poketest2.presentation.main.adapter.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.roman.poketest2.databinding.ItemPokemonGridBinding
import com.roman.poketest2.domain.PokemonUi
import com.roman.poketest2.presentation.main.adapter.PokemonDiffCallback

class PokemonGridAdapter(private val onItemClick: (pokemonUi: PokemonUi) -> Unit) :
    ListAdapter<PokemonUi, PokemonGridViewHolder>(PokemonDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonGridViewHolder {
        val binding =
            ItemPokemonGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonGridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonGridViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { onItemClick(getItem(position)) }
    }

}

