package com.roman.poketest2.di

import android.content.Context
import androidx.room.Room
import com.roman.poketest2.data.local.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryDiModule {

    @Provides
    fun provideLocalRepository(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(appContext, PokemonDatabase::class.java, "pokedb")
            .build()
            .getPokemonDao()


}