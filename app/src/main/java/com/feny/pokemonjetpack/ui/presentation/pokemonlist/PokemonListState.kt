package com.feny.pokemonjetpack.ui.theme.presentation.pokemonlist

import com.feny.pokemonjetpack.domain.model.Pokemon

data class PokemonListState(
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val pokemonList: List<Pokemon> = emptyList(),
    val query: String = "",
    val error: String? = null,
    val page: Int = 0
)
