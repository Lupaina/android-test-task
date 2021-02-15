package com.example.filmsapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.filmsapplication.models.FilmEntity

@Database(entities = [FilmEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun filmDao(): FilmsDao

    companion object {
        const val DATABASE_NAME = "stick_database"

    }
}