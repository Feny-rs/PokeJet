package com.feny.pokemonjetpack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.feny.pokemonjetpack.data.local.dao.PokemonDao
import com.feny.pokemonjetpack.data.local.entities.Converters
import com.feny.pokemonjetpack.data.local.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}