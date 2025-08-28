package com.feny.pokemonjetpack.ui.theme.presentation.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feny.pokemonjetpack.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state: StateFlow<PokemonListState> = _state

    private val _isGrid = MutableStateFlow(false)
    val isGrid: StateFlow<Boolean> = _isGrid

    private val limit = 20

    init {
        loadPokemon(page = 0)
    }

    fun toggleLayout() {
        _isGrid.value = !_isGrid.value
    }

    private fun loadPokemon(page: Int) {
        viewModelScope.launch {
            val query = _state.value.query
            if (page == 0) _state.value = _state.value.copy(isLoading = true, pokemonList = emptyList())
            else _state.value = _state.value.copy(isLoadingMore = true)

            val offset = page * limit
            val pokemons = if (query.isBlank()) {
                repository.getPokemonList(limit, offset)
            } else {
                repository.getAllPokemon().filter { it.name.contains(query, ignoreCase = true) }
            }

            if (page == 0) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    pokemonList = pokemons,
                    page = page
                )
            } else {
                _state.value = _state.value.copy(
                    isLoadingMore = false,
                    pokemonList = _state.value.pokemonList + pokemons,
                    page = page
                )
            }
        }
    }

    fun loadNextPage() {
        val query = _state.value.query
        if (_state.value.isLoading || _state.value.isLoadingMore || query.isNotBlank()) return
        val nextPage = _state.value.page + 1
        loadPokemon(nextPage)
    }

    fun onSearch(query: String) {
        _state.value = _state.value.copy(query = query, page = 0, pokemonList = emptyList())
        loadPokemon(0)
    }
}
