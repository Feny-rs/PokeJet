package com.feny.pokemonjetpack.ui.theme.presentation.pokemondetail

import com.feny.pokemonjetpack.domain.model.Pokemon

data class DetailPokemonState(
    val isLoading: Boolean = false,
    val pokemon: Pokemon? = null,
    val error: String? = null
)
