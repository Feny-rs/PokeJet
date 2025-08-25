package com.feny.pokemonjetpack.domain.repository

import com.feny.pokemonjetpack.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon>
    suspend fun getPokemonDetail(name: String): Pokemon
}
