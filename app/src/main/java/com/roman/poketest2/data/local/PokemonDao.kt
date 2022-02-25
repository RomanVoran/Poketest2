package com.roman.poketest2.data.local

import androidx.room.*
import com.roman.poketest2.domain.PokemonLocal

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemonlocal WHERE id = :id")
    suspend fun getPokemonById(id: Int): PokemonLocal

    @Query("SELECT * FROM pokemonlocal WHERE name = :name")
    suspend fun getPokemonById(name: String): PokemonLocal

    @Query("SELECT * FROM pokemonlocal")
    suspend fun getAllPokemons(): List<PokemonLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPokemon(pokemon: PokemonLocal)

    @Delete
    suspend fun removeAllPokemons(pokemon: PokemonLocal)

}