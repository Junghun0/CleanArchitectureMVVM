package com.example.cleanarchitecture.data.main.repository.impl

import com.example.cleanarchitecture.data.main.dto.PokemonInfo
import com.example.cleanarchitecture.data.main.dto.PokemonResponse
import com.example.cleanarchitecture.data.main.repository.PokemonRepository
import com.example.cleanarchitecture.data.main.service.PokemonService
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService
): PokemonRepository {
    override suspend fun pokemonList(limit: Int, offset: Int): List<PokemonResponse> {
        return pokemonService.fetchPokemonList(limit, offset)
    }

    override suspend fun pokemonInfo(name: String): PokemonInfo {
        return pokemonService.fetchPokemonInfo(name)
    }
}