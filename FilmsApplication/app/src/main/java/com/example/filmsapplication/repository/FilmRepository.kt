package com.example.filmsapplication.repository

import com.example.filmsapplication.data.retrofit.ApiService
import com.example.filmsapplication.data.room.FilmsDao
import com.example.filmsapplication.menagers.AuthorizationManager
import com.example.filmsapplication.models.Film
import com.example.filmsapplication.models.Response
import com.example.filmsapplication.util.mapper.CacheMapper

class FilmRepository(
    private val apiService: ApiService,
    private val filmsDao: FilmsDao
) {

    suspend fun getFilms(page: Int): Response<Film> {
        return apiService.getFilms(AuthorizationManager.apiKey, page)
    }

    suspend fun savePage(list: List<Film>) {
        val entityList = list.map { CacheMapper.mapToEntity(it) }
        filmsDao.insertFilms(entityList)
    }

    suspend fun getFirstPage(): List<Film> {
        return filmsDao.getAll().map { CacheMapper.mapFromEntity(it) }
    }

    suspend fun clearDBData(){
        filmsDao.clearTable()
    }
}