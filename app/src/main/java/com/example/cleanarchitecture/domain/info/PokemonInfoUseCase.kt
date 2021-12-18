package com.example.cleanarchitecture.domain.info

import com.example.cleanarchitecture.data.main.dto.PokemonInfo
import com.example.cleanarchitecture.data.main.repository.PokemonRepository
import javax.inject.Inject

class PokemonInfoUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    data class Params(val name: String)

    suspend operator fun invoke(infoParams: Params): PokemonInfo =
        pokemonRepository.pokemonInfo(infoParams.name)
}