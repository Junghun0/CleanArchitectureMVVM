package com.example.cleanarchitecture.presenter.main

import androidx.paging.*
import com.example.cleanarchitecture.data.main.dto.Pokemon

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    val query: String
) : RemoteMediator<Int, Pokemon>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Pokemon>
    ): MediatorResult {
        TODO("Not yet implemented")
    }

}
