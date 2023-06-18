package com.roman.poketest2.presentation.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.roman.poketest2.domain.PokemonUi

object PokemonDiffCallback : DiffUtil.ItemCallback<PokemonUi>() {
    override fun areItemsTheSame(oldItem: PokemonUi, newItem: PokemonUi) = oldItem == newItem


    override fun areContentsTheSame(oldItem: PokemonUi, newItem: PokemonUi) =
        oldItem.id == newItem.id
}