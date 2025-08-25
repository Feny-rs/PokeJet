package com.feny.pokemonjetpack.data.network.pokeapi.response

import com.feny.pokemonjetpack.domain.model.Pokemon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("sprites")
    val sprites: SpriteResponse? = null,
    @SerialName("moves")
    val moves: List<MoveResponse>? = null,
    @SerialName("weight")
    val weight: Int? = null,
    @SerialName("height")
    val height: Int? = null,
    @SerialName("abilities")
    val abilities: List<AbilityResponse>? = null,
    @SerialName("types")
    val types: List<TypeResponse>? = null,
    @SerialName("stats")
    val stats: List<StatResponse>? = null
)

@Serializable
data class SpriteResponse(
    @SerialName("front_default")
    val frontImage: String? = null,
    @SerialName("back_default")
    val backImage: String? = null
)

@Serializable
data class AbilityResponse(
    @SerialName("ability")
    val ability: AbilityDetailResponse? = null,
    @SerialName("is_hidden")
    val isHidden: Boolean? = null,
    @SerialName("slot")
    val slot: Int? = null,
)

@Serializable
data class AbilityDetailResponse(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class MoveResponse(
    @SerialName("move")
    val move: MoveDetailResponse? = null
)

@Serializable
data class MoveDetailResponse(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class TypeResponse(
    @SerialName("slot")
    val slot: Int? = null,
    @SerialName("type")
    val type: TypeDetailResponse? = null
)

@Serializable
data class TypeDetailResponse(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class StatResponse(
    @SerialName("base_stat")
    val baseStat: Int? = null,
    @SerialName("effort")
    val effort: Int? = null,
    @SerialName("stat")
    val stat: StatDetailResponse? = null
)

@Serializable
data class StatDetailResponse(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

fun PokemonDetailResponse.toDomain(): Pokemon {
    return Pokemon(
        id = id ?: error("Pokemon detail missing id"),
        name = name ?: "Unknown",
        imageUrl = sprites?.frontImage ?: "",
        abilities = abilities?.mapNotNull { it.ability?.name } ?: emptyList(),
        height = height,
        weight = weight,
        types = types?.mapNotNull { it.type?.name } ?: emptyList(),
        stats = stats?.associate { stat ->
            (stat.stat?.name ?: "unknown") to (stat.baseStat ?: 0)
        } ?: emptyMap()
    )
}
