package com.example.cleanarchitecture.domain.main

import com.example.cleanarchitecture.data.main.dto.PokemonResponse
import com.example.cleanarchitecture.data.main.repository.PokemonRepository
import javax.inject.Inject

class PokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    data class Params(val limit: Int, val offset: Int)

    suspend operator fun invoke(listParams: Params): PokemonResponse =
        pokemonRepository.pokemonList(listParams.limit, listParams.offset)
}