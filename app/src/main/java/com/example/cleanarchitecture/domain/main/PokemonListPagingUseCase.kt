package com.example.cleanarchitecture.domain.main

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.cleanarchitecture.data.main.dto.Pokemon
import com.example.cleanarchitecture.presenter.main.PokemonPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonListPagingUseCase @Inject constructor(
    private val pokemonListUseCase: PokemonListUseCase,
) {
    operator fun invoke(): Flow<PagingData<Pokemon>> =
        Pager(PagingConfig(pageSize = 1)) {
            PokemonPagingSource(pokemonListUseCase)
        }.flow
}