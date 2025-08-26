package com.feny.pokemonjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.feny.pokemonjetpack.ui.theme.PokemonJetpackTheme
import com.feny.pokemonjetpack.ui.theme.SetupSystemBars
import com.feny.pokemonjetpack.ui.theme.presentation.AppNavGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var showSplash by mutableStateOf(true)

        setContent {
            PokemonJetpackTheme {
                SetupSystemBars()
                if (showSplash) {
                    // Splash Screen Compose
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(R.color.pokemon_background)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.logo_pokemon),
                            contentDescription = null,
                            modifier = Modifier.size(320.dp)
                        )
                    }
                } else {
                    // Main App
                    val navController = rememberNavController()
                    AppNavGraph(navController)
                }
            }
        }

        // Delay 2 detik sebelum pindah ke Main
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000L)
            showSplash = false
            splashScreen.setKeepOnScreenCondition { false }
        }
    }
}
