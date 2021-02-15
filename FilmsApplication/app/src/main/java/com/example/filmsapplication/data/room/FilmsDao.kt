package com.example.filmsapplication.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filmsapplication.models.FilmEntity

@Dao
interface FilmsDao {

    @Query("SELECT * FROM films_first_page")
    suspend fun getAll(): List<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(films: List<FilmEntity>)

    @Query("DELETE FROM films_first_page")
    suspend fun clearTable()
}
