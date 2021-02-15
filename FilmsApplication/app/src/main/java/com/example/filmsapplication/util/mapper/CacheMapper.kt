package com.example.filmsapplication.util.mapper

import com.example.filmsapplication.models.Film
import com.example.filmsapplication.models.FilmEntity
import com.example.filmsapplication.util.extansions.convertFromDate
import com.example.filmsapplication.util.extansions.convertToDate

object CacheMapper : EntityMapper<FilmEntity, Film> {
    override fun mapToEntity(model: Film) =
        FilmEntity(
            id = model.id,
            overview = model.overview,
            backdropPath = model.backdropPath,
            releaseDate = model.releaseDate.convertToDate(),
            title = model.title,
            vote_average = model.vote_average,
        )

    override fun mapFromEntity(entity: FilmEntity) =
        Film(
            id = entity.id,
            overview = entity.overview,
            backdropPath = entity.backdropPath,
            releaseDate = entity.releaseDate.convertFromDate(),
            title = entity.title,
            vote_average = entity.vote_average
        )

}