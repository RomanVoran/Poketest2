package com.roman.poketest2.presentation.main.adapter.grid

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.roman.poketest2.R
import com.roman.poketest2.databinding.ItemPokemonGridBinding
import com.roman.poketest2.domain.PokemonUi
import com.roman.poketest2.utils.titlecaseFirstChar

class PokemonGridViewHolder(private val binding: ItemPokemonGridBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pokeData: PokemonUi) = with(binding) {
        binding.pokemonName.text = pokeData.name.titlecaseFirstChar()
        GlideToVectorYou
            .init()
            .with(binding.root.context)
            .setPlaceHolder(R.drawable.ic_placeholder_image, R.drawable.ic_placeholder_image_error)
            .load(pokeData.imageUrl.toUri(), binding.pokemonAvatar)
    }
}