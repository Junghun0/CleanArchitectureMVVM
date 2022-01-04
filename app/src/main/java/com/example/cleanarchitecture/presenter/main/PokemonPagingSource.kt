package com.example.cleanarchitecture.presenter.main

import androidx.paging.*
import com.example.cleanarchitecture.data.main.dto.Pokemon
import com.example.cleanarchitecture.domain.main.PokemonListUseCase
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val pokemonListUseCase: PokemonListUseCase
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val next = params.key ?: 0
            val offset = if (next == 0) 0 else next + ITEMS_PER_PAGE
            val list = pokemonListUseCase(PokemonListUseCase.Params(ITEMS_PER_PAGE, offset))
            LoadResult.Page(
                data = list.results,
                prevKey = if (next == 0) null else next - 1,
                nextKey = next + ITEMS_PER_PAGE
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        const val ITEMS_PER_PAGE = 20
    }
}
