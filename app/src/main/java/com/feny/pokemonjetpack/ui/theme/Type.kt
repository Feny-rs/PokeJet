package com.feny.pokemonjetpack.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.feny.pokemonjetpack.R

val PressStart2P = FontFamily(
    Font(R.font.press_start_2p, weight = FontWeight.Normal)
)

val VT323 = FontFamily(
    Font(R.font.vt323)
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = PressStart2P,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = PressStart2P,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = PressStart2P,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = VT323,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = VT323,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = PressStart2P,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)
