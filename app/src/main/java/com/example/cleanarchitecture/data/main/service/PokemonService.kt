package com.example.cleanarchitecture.data.main.service

import com.example.cleanarchitecture.data.main.dto.PokemonInfo
import com.example.cleanarchitecture.data.main.dto.PokemonResponse
import dagger.Provides
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): List<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(@Path("name") name: String): PokemonInfo
}