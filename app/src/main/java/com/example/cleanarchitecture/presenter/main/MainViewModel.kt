package com.example.cleanarchitecture.presenter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.cleanarchitecture.data.main.dto.Pokemon
import com.example.cleanarchitecture.data.main.dto.PokemonInfo
import com.example.cleanarchitecture.data.main.dto.PokemonResponse
import com.example.cleanarchitecture.domain.main.PokemonListUseCase
import com.example.cleanarchitecture.domain.base.BaseViewModel
import com.example.cleanarchitecture.domain.info.PokemonInfoUseCase
import com.example.cleanarchitecture.domain.main.PokemonListPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonListUseCase: PokemonListUseCase,
    private val pokemonInfoUseCase: PokemonInfoUseCase,
    private val pokemonListPagingUseCase: PokemonListPagingUseCase
): BaseViewModel(){

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>>
        get() = _pokemonList

    private val _pokemonInfo = MutableLiveData<PokemonInfo>()
    val pokemonInfo: LiveData<PokemonInfo>
        get() = _pokemonInfo

    fun fetchPokemonList(limit: Int, offset: Int) {
        viewModelScope.launch {
            val list = pokemonListUseCase(PokemonListUseCase.Params(limit, offset))
            _pokemonList.value = list.results
        }
    }

    fun fetchPokemonInfo(name: String) {
        viewModelScope.launch {
            val info = pokemonInfoUseCase(PokemonInfoUseCase.Params(name))
            _pokemonInfo.value = info
        }
    }

    fun pagingPokemonList(): Flow<PagingData<Pokemon>> {
        return pokemonListPagingUseCase()
    }
}