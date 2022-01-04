package com.example.cleanarchitecture.presenter.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.main.dto.PokemonInfo
import com.example.cleanarchitecture.domain.base.BaseViewModel
import com.example.cleanarchitecture.domain.info.PokemonInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val pokemonInfoUseCase: PokemonInfoUseCase
) : BaseViewModel() {

    private val _pokemonInfo = MutableLiveData<PokemonInfo>()
    val pokemonInfo: LiveData<PokemonInfo>
        get() = _pokemonInfo

    fun fetchPokemonInfo(name: String) {
        viewModelScope.launch {
            runCatching {
                pokemonInfoUseCase(PokemonInfoUseCase.Params(name))
            }.onSuccess {
                _pokemonInfo.value = it
            }.onFailure {

            }
        }
    }
}