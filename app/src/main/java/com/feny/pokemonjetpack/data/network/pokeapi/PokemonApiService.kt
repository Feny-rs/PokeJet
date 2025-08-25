package com.feny.pokemonjetpack.data.network.pokeapi

import com.feny.pokemonjetpack.data.network.pokeapi.response.PokemonDetailResponse
import com.feny.pokemonjetpack.data.network.pokeapi.response.PokemonListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import io.ktor.http.takeFrom

class PokemonApiService(private val client: HttpClient, private val baseUrl: String = "https://pokeapi.co/api/v2/") {

    suspend fun getPokemonList(limit: Int = 20, offset: Int = 0): PokemonListResponse {
        return client.get {
            url {
                takeFrom(baseUrl)
                appendPathSegments("pokemon")
                parameters.append("limit", limit.toString())
                parameters.append("offset", offset.toString())
            }
        }.body()
    }

    suspend fun getPokemonDetail(name: String): PokemonDetailResponse {
        return client.get {
            url {
                takeFrom(baseUrl)
                appendPathSegments("pokemon", name)
            }
        }.body()
    }
}
