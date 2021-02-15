package com.example.filmsapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.filmsapplication.data.room.converters.DateConverter
import java.util.*

@Entity(tableName = "films_first_page")
@TypeConverters(DateConverter::class)
data class FilmEntity(
    @PrimaryKey
    val id: Long,
    val overview: String,
    val backdropPath: String,
    val releaseDate: Date?,
    val title: String,
    val vote_average: Double
)
