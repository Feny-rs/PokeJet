package com.feny.pokemonjetpack.data.network.pokeapi.response

import com.feny.pokemonjetpack.domain.model.Pokemon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("next")
    val next: String? = null,
    @SerialName("previous")
    val previous: String? = null,
    @SerialName("results")
    val results: List<PokemonItemResponse> = arrayListOf()
)

@Serializable
data class PokemonItemResponse(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null,
)

fun PokemonItemResponse.toDomain(): Pokemon {
    val id = url?.trimEnd('/')?.split("/")?.last()?.toIntOrNull()
        ?: error("Invalid Pokemon URL: $url")

    return Pokemon(
        id = id,
        name = name ?: "Unknown",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
        abilities = emptyList() // list API memang tidak ada abilities
    )
}
