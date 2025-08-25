package com.feny.pokemonjetpack

import android.app.Application
import com.feny.pokemonjetpack.di.databaseModule
import com.feny.pokemonjetpack.di.networkModule
import com.feny.pokemonjetpack.di.repositoryModule
import com.feny.pokemonjetpack.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@MyApp)

            // load modules
            modules(
                networkModule,
                databaseModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}