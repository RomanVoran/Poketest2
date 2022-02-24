package com.roman.poketest2.data.local

import androidx.room.*
import com.roman.poketest2.domain.PokemonLocal

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemonlocal WHERE id = :id")
    fun getPokemonById(id: Int): PokemonLocal

    @Query("SELECT * FROM pokemonlocal WHERE name = :name")
    fun getPokemonById(name: String): PokemonLocal

    @Query("SELECT * FROM pokemonlocal")
    fun getAllPokemons(): List<PokemonLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPokemon(pokemon: PokemonLocal)

    @Delete
    fun removeAllPokemons(pokemon: PokemonLocal)

}