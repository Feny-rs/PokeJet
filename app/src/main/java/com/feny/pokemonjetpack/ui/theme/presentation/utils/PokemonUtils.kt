package com.feny.pokemonjetpack.ui.theme.presentation.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getTypeColor(type: String): Color {
    return when (type.lowercase()) {
        "grass" -> Color(0xFF78C850)
        "fire" -> Color(0xFFF08030)
        "water" -> Color(0xFF6890F0)
        "electric" -> Color(0xFFF8D030)
        "ice" -> Color(0xFF98D8D8)
        "fighting" -> Color(0xFFC03028)
        "poison" -> Color(0xFFA040A0)
        "ground" -> Color(0xFFE0C068)
        "flying" -> Color(0xFFA890F0)
        "psychic" -> Color(0xFFF85888)
        "bug" -> Color(0xFFA8B820)
        "rock" -> Color(0xFFB8A038)
        "ghost" -> Color(0xFF705898)
        "dragon" -> Color(0xFF7038F8)
        "dark" -> Color(0xFF705848)
        "steel" -> Color(0xFFB8B8D0)
        "fairy" -> Color(0xFFEE99AC)
        else -> MaterialTheme.colorScheme.primary
    }
}

@Composable
fun getStatColor(statName: String): Color {
    return when (statName.lowercase()) {
        "hp" -> Color(0xFF4CAF50)       // Green
        "attack" -> Color(0xFFF44336)   // Red
        "defense" -> Color(0xFF2196F3)  // Blue
        "speed" -> Color(0xFFFFC107)    // Amber
        "special-attack" -> Color(0xFF9C27B0) // Purple
        "special-defense" -> Color(0xFF00BCD4) // Cyan
        else -> MaterialTheme.colorScheme.primary
    }
}
