package com.roman.poketest2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roman.poketest2.domain.PokemonLocal

@Database(entities = [PokemonLocal::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

}