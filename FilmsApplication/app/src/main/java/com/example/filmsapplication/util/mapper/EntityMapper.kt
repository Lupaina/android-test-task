package com.example.filmsapplication.util.mapper

interface EntityMapper<E, M> {
    fun mapToEntity(model: M): E
    fun mapFromEntity(entity: E): M
}