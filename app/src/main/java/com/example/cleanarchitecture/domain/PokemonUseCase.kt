package com.example.cleanarchitecture.domain

import com.example.cleanarchitecture.data.main.dto.PokemonInfo
import com.example.cleanarchitecture.data.main.dto.PokemonResponse
import com.example.cleanarchitecture.data.main.repository.PokemonRepository
import javax.inject.Inject

class PokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    data class ListParams(val limit: Int, val offset: Int)

    suspend fun fetchPokemonList(listParams: ListParams): List<PokemonResponse> {
        return pokemonRepository.pokemonList(listParams.limit, listParams.offset)
    }

    data class InfoParams(val name: String)

    suspend fun fetchPokemonInfo(infoParams: InfoParams): PokemonInfo {
        return pokemonRepository.pokemonInfo(infoParams.name)
    }


}