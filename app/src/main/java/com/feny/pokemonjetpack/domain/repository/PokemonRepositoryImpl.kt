package com.feny.pokemonjetpack.domain.repository

import com.feny.pokemonjetpack.data.local.dao.PokemonDao
import com.feny.pokemonjetpack.data.local.entities.toDomain
import com.feny.pokemonjetpack.data.local.entities.toEntity
import com.feny.pokemonjetpack.data.network.pokeapi.PokemonApiService
import com.feny.pokemonjetpack.data.network.pokeapi.response.toDomain
import com.feny.pokemonjetpack.domain.model.Pokemon
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.firstOrNull

class PokemonRepositoryImpl(
    private val api: PokemonApiService,
    private val dao: PokemonDao
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon> {
        // Take from cache first
        val cached = dao.getAllPokemon().firstOrNull()
        if (cached != null && cached.isNotEmpty()) {
            val slice = cached.drop(offset).take(limit)
            if (slice.isNotEmpty()) return slice.map { it.toDomain() }
        }

        // Get from API
        val response = api.getPokemonList(limit, offset)

        // Save each Pokémon's details to the cache asynchronously
        val list = coroutineScope {
            response.results.map { item ->
                async {
                    val detail = api.getPokemonDetail(item.name ?: "")
                    detail.toDomain()
                }
            }.awaitAll()
        }

        dao.insertPokemonList(list.map { it.toEntity() })
        return list
    }

    override suspend fun getAllPokemon(): List<Pokemon> {
        val cached = dao.getAllPokemon().firstOrNull()
        return if (!cached.isNullOrEmpty()) {
            cached.map { it.toDomain() }
        } else {
            // By and large, this will fetch all data from the API.
            getPokemonList(1000, 0)
        }
    }

    override suspend fun getPokemonDetail(name: String): Pokemon {
        return runCatching {
            api.getPokemonDetail(name).toDomain()
        }.getOrElse { ex ->
            val cached = dao.getAllPokemon().firstOrNull()?.firstOrNull { it.name == name }
            cached?.toDomain() ?: throw ex
        }
    }
}
