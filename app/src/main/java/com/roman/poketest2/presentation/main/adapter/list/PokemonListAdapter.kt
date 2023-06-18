package com.roman.poketest2.presentation.main.adapter.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.roman.poketest2.databinding.ItemPokemonListBinding
import com.roman.poketest2.domain.PokemonUi
import com.roman.poketest2.presentation.main.adapter.PokemonDiffCallback

class PokemonListAdapter(private val onItemClick: (pokemonUi: PokemonUi) -> Unit) :
    ListAdapter<PokemonUi, PokemonListViewHolder>(PokemonDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val binding =
            ItemPokemonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { onItemClick(getItem(position)) }
    }

}
