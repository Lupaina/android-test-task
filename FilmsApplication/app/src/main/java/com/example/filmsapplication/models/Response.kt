package com.example.filmsapplication.models

import com.google.gson.annotations.SerializedName

class Response<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<T>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
)