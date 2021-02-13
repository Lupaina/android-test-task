package com.example.filmsapplication.repository

import com.example.filmsapplication.menagers.AuthorizationManager
import com.example.filmsapplication.models.Film
import com.example.filmsapplication.models.Response
import com.example.filmsapplication.retrofit.ApiService

class FilmRepository(
    private val apiService: ApiService,
) {

    suspend fun getFilms(page: Int): Response<Film> {
        return apiService.getFilms(AuthorizationManager.apiKey, page)
    }

    /*suspend fun getFilms(page:Int): Flow<Resource<Response<Film>>> = flow {
        emit(Resource.Loading<Response<Film>>())
        try {
            val response = apiService.getFilms(AuthorizationManager.apiKey,pa)
            emit(Resource.Success(response))
        } catch (e: Throwable) {
            emit(Resource.Error<Response<Film>>(message = e.message))
        }
    }*/
}