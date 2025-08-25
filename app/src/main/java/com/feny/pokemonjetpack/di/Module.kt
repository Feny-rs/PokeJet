package com.feny.pokemonjetpack.di

import androidx.room.Room
import com.feny.pokemonjetpack.data.local.AppDatabase
import com.feny.pokemonjetpack.data.network.pokeapi.PokemonApiService
import com.feny.pokemonjetpack.domain.repository.PokemonRepository
import com.feny.pokemonjetpack.domain.repository.PokemonRepositoryImpl
import com.feny.pokemonjetpack.ui.theme.presentation.detail.DetailPokemonViewModel
import com.feny.pokemonjetpack.ui.theme.presentation.pokemonlist.PokemonListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
    single { PokemonApiService(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "pokemon_db"
        ).build()
    }
    single { get<AppDatabase>().pokemonDao() }
}

val repositoryModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel { PokemonListViewModel(get()) }
    viewModel { DetailPokemonViewModel(get()) }
}
