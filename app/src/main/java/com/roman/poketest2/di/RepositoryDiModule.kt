package com.roman.poketest2.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.roman.poketest2.data.local.PokemonDatabase
import com.roman.poketest2.data.remote.PokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://pokeapi.co/api/v2/"


@Module
@InstallIn(SingletonComponent::class)
object RepositoryDiModule {

    @Singleton
    @Provides
    fun provideLocalRepository(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(appContext, PokemonDatabase::class.java, "pokedb")
            .build()
            .getPokemonDao()


    @Singleton
    @Provides
    fun provideRemoteRepository(@ApplicationContext appContext: Context) =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(PokemonApi::class.java)


}