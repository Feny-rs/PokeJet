package com.feny.pokemonjetpack.ui.theme.presentation.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feny.pokemonjetpack.domain.model.Pokemon
import com.feny.pokemonjetpack.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state: StateFlow<PokemonListState> = _state

    private val limit = 20

    init {
        loadPokemon(page = 0, query = "")
    }

    private fun loadPokemon(page: Int, query: String) {
        viewModelScope.launch {
            if (page == 0) {
                _state.value = _state.value.copy(isLoading = true, pokemonList = emptyList())
            } else {
                _state.value = _state.value.copy(isLoadingMore = true)
            }

            val offset = page * limit

            val pokemons = if (query.isBlank()) {
                // Pagination normal saat query kosong
                repository.getPokemonList(limit, offset)
            } else {
                // Search: ambil semua list
                repository.getPokemonList(1000, 0)
                    .filter { it.name.contains(query, ignoreCase = true) }
            }

            if (page == 0) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    pokemonList = pokemons,
                    page = page,
                    query = query
                )
            } else {
                _state.value = _state.value.copy(
                    isLoadingMore = false,
                    pokemonList = _state.value.pokemonList + pokemons,
                    page = page,
                    query = query
                )
            }
        }
    }

    fun loadNextPage() {
        if (_state.value.isLoadingMore || _state.value.isLoading || _state.value.query.isNotBlank()) return
        val nextPage = _state.value.page + 1
        loadPokemon(nextPage, _state.value.query)
    }

    fun onSearch(query: String) {
        _state.value = _state.value.copy(
            query = query,
            page = 0,
            pokemonList = emptyList()
        )
        loadPokemon(page = 0, query = query)
    }
}
