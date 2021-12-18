package com.example.cleanarchitecture.data.main.repository

import com.example.cleanarchitecture.data.main.dto.PokemonInfo
import com.example.cleanarchitecture.data.main.dto.PokemonResponse

interface PokemonRepository {
    suspend fun pokemonList(
        limit: Int,
        offset: Int
    ): PokemonResponse

    suspend fun pokemonInfo(
        name: String
    ): PokemonInfo
}