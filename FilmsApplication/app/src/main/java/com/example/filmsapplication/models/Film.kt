package com.example.filmsapplication.models

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("id")
    val id: Long,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("release_date")
    val releaseDate: String?, // don't change to Date type, some field has a blank string
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val vote_average: Double,
    var isFavorite: Boolean = false,
    var mainListPosition: Int = 0
)