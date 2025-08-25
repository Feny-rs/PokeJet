package com.feny.pokemonjetpack.data.repository

import com.feny.pokemonjetpack.data.network.pokeapi.PokemonApiService
import com.feny.pokemonjetpack.data.network.pokeapi.response.toDomain
import com.feny.pokemonjetpack.domain.model.Pokemon

class PokemonRepositoryImpl(
    private val api: PokemonApiService
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon> {
        val response = api.getPokemonList(limit, offset)
        return response.results.map { it.toDomain() }
    }

    override suspend fun getPokemonDetail(name: String): Pokemon {
        val response = api.getPokemonDetail(name)
        return response.toDomain()
    }
}
