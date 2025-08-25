package com.feny.pokemonjetpack.ui.theme.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feny.pokemonjetpack.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailPokemonViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailPokemonState())
    val state: StateFlow<DetailPokemonState> = _state

    fun loadPokemonDetail(name: String) {
        viewModelScope.launch {
            try {
                _state.value = DetailPokemonState(isLoading = true)
                val pokemon = repository.getPokemonDetail(name)
                _state.value = DetailPokemonState(isLoading = false, pokemon = pokemon)
            } catch (e: Exception) {
                _state.value = DetailPokemonState(isLoading = false, error = e.message)
            }
        }
    }
}
