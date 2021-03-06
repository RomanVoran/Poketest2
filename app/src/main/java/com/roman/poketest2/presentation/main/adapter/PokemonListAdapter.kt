package com.roman.poketest2.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.roman.poketest2.databinding.ItemPokemonListBinding
import com.roman.poketest2.domain.PokemonUi

class PokemonListAdapter(private val onClick: (id: Int) -> Unit) :
    ListAdapter<PokemonUi, PokemonListViewHolder>(PokemonDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val binding =
            ItemPokemonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { onClick(position) }
    }

}

object PokemonDiffCallback : DiffUtil.ItemCallback<PokemonUi>() {
    override fun areItemsTheSame(oldItem: PokemonUi, newItem: PokemonUi) = oldItem == newItem


    override fun areContentsTheSame(oldItem: PokemonUi, newItem: PokemonUi) =
        oldItem.id == newItem.id

}