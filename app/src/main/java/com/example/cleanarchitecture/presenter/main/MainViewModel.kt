package com.example.cleanarchitecture.presenter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.cleanarchitecture.data.main.dto.Pokemon
import com.example.cleanarchitecture.domain.main.PokemonListUseCase
import com.example.cleanarchitecture.domain.base.BaseViewModel
import com.example.cleanarchitecture.domain.main.PokemonListPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonListUseCase: PokemonListUseCase,
    private val pokemonListPagingUseCase: PokemonListPagingUseCase
): BaseViewModel(){

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>>
        get() = _pokemonList

    fun fetchPokemonList(limit: Int, offset: Int) {
        viewModelScope.launch {
            val list = pokemonListUseCase(PokemonListUseCase.Params(limit, offset))
            _pokemonList.value = list.results
        }
    }

    fun pagingPokemonList(): Flow<PagingData<Pokemon>> =
        pokemonListPagingUseCase()

}