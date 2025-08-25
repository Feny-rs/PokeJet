package com.feny.pokemonjetpack.ui.theme.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.feny.pokemonjetpack.ui.theme.presentation.detail.DetailPokemonScreen
import com.feny.pokemonjetpack.ui.theme.presentation.pokemonlist.PokemonListScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "list") {
        composable("list") {
            PokemonListScreen(
                onPokemonClick = { name ->
                    navController.navigate("detail/$name")
                }
            )
        }
        composable("detail/{pokemonName}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("pokemonName") ?: ""
            DetailPokemonScreen(
                pokemonName = name,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
