package com.feny.pokemonjetpack.ui.theme.presentation.pokemondetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.feny.pokemonjetpack.R
import com.feny.pokemonjetpack.ui.theme.presentation.common.StatBar
import com.feny.pokemonjetpack.ui.theme.presentation.common.TypeChip
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPokemonScreen(
    pokemonName: String,
    viewModel: DetailPokemonViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(pokemonName) {
        viewModel.loadPokemonDetail(pokemonName)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        pokemonName.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> CircularProgressIndicator()

                state.error != null -> Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(stringResource(R.string.error, state.error ?: ""))
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { viewModel.loadPokemonDetail(pokemonName) }) {
                        Text(stringResource(R.string.retry))
                    }
                }

                state.pokemon != null -> {
                    val pokemon = state.pokemon
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(pokemon?.imageUrl)
                                    .crossfade(true)
                                    .placeholder(R.drawable.ic_placeholder)
                                    .error(R.drawable.ic_placeholder)
                                    .build()
                            ),
                            contentDescription = pokemon?.name,
                            modifier = Modifier.size(150.dp)
                        )

                        Spacer(Modifier.height(8.dp))

                        if (pokemon != null) {
                            Text(
                                text = pokemon.name.replaceFirstChar { it.uppercase() },
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }

                        Spacer(Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text("Height: ${pokemon?.height?.div(10.0)} m")
                            Text("Weight: ${pokemon?.weight?.div(10.0)} kg")
                        }

                        Spacer(Modifier.height(12.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            pokemon?.types?.forEach { type -> TypeChip(type) }
                        }

                        Spacer(Modifier.height(16.dp))

                        Text(
                            stringResource(R.string.abilities),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        pokemon?.abilities?.forEach { ability -> Text("- $ability") }

                        Spacer(Modifier.height(16.dp))

                        Text(
                            stringResource(R.string.stats),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        pokemon?.stats?.forEach { (stat, value) ->
                            StatBar(stat, value)
                        }
                    }
                }
            }
        }
    }
}
