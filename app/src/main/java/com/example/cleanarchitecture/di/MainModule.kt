package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.data.main.repository.PokemonRepository
import com.example.cleanarchitecture.data.main.repository.impl.PokemonRepositoryImpl
import com.example.cleanarchitecture.data.main.service.PokemonService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainModule {

    @Binds
    @ViewModelScoped
    abstract fun bindPokemonRepository(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository

    companion object {
        @Provides
        @ViewModelScoped
        fun provideRetrofit(): PokemonService {
            return Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokemonService::class.java)
        }
    }

}