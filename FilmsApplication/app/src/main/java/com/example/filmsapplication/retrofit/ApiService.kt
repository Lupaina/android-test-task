package com.example.filmsapplication.retrofit

import com.example.filmsapplication.models.Film
import com.example.filmsapplication.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie")
    suspend fun getFilms(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<Film>
}