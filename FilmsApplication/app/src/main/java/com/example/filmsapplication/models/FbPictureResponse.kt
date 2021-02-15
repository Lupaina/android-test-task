package com.example.filmsapplication.models

import com.google.gson.annotations.SerializedName

data class GraphObject(
    @SerializedName("picture")
    val picture: Picture
)

class Picture(
    @SerializedName("data")
    val data: Data
)

data class Data(
    @SerializedName("url")
    val url: String
)




