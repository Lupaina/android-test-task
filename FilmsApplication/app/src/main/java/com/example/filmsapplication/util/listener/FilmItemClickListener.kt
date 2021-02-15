package com.example.filmsapplication.util.listener

interface FilmItemClickListener {
    fun like(position:Int)
    fun share(position:Int)
}