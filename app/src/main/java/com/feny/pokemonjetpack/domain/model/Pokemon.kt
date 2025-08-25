package com.feny.pokemonjetpack.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val abilities: List<String>,
    val height: Int? = null,
    val weight: Int? = null,
    val types: List<String> = emptyList(),
    val stats: Map<String, Int> = emptyMap()
)
