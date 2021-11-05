package com.example.cleanarchitecture.presenter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.main.dto.PokemonInfo
import com.example.cleanarchitecture.data.main.dto.PokemonResponse
import com.example.cleanarchitecture.domain.PokemonUseCase
import com.example.cleanarchitecture.domain.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
): BaseViewModel(){

    private val _pokemonList = MutableLiveData<List<PokemonResponse>>()
    val pokemonList: LiveData<List<PokemonResponse>>
        get() = _pokemonList

    private val _pokemonInfo = MutableLiveData<PokemonInfo>()
    val pokemonInfo: LiveData<PokemonInfo>
        get() = _pokemonInfo

    fun fetchPokemonList(limit: Int, offset: Int) {
        viewModelScope.launch {
            val list = pokemonUseCase.fetchPokemonList(PokemonUseCase.ListParams(limit, offset))
            _pokemonList.value = list
        }
    }

    fun fetchPokemonInfo(name: String) {
        viewModelScope.launch {
            val info = pokemonUseCase.fetchPokemonInfo(PokemonUseCase.InfoParams(name))
            _pokemonInfo.value = info
        }

    }
}