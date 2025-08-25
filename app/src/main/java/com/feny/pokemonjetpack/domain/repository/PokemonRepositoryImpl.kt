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
import kotlinx.coroutines.flow.first

class PokemonRepositoryImpl(
    private val api: PokemonApiService,
    private val dao: PokemonDao
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon> {
        // 1. Cek cache (ambil sekali dari Flow)
        val cached = dao.getAllPokemon().first()
        if (cached.isNotEmpty()) {
            return cached.map { it.toDomain() }
        }

        // 2. Ambil dari API secara paralel
        val response = api.getPokemonList(limit, offset)

        val list = coroutineScope {
            response.results.map { item ->
                async {
                    val detail = api.getPokemonDetail(item.name ?: "")
                    detail.toDomain()
                }
            }.awaitAll()
        }

        // 3. Simpan ke cache
        dao.insertPokemonList(list.map { it.toEntity() })

        return list
    }

    override suspend fun getPokemonDetail(name: String): Pokemon {
        return runCatching {
            api.getPokemonDetail(name).toDomain()
        }.getOrElse { ex ->
            // fallback ke cache kalau ada
            val cached = dao.getAllPokemon().first().firstOrNull { it.name == name }
            cached?.toDomain() ?: throw ex
        }
    }
}
