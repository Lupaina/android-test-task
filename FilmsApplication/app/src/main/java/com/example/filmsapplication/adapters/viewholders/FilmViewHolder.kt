package com.example.filmsapplication.adapters.viewholders

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapplication.databinding.LayoutItemFilmBinding
import com.example.filmsapplication.models.Film
import com.squareup.picasso.Picasso

class FilmViewHolder(val uiBinding: LayoutItemFilmBinding) :
    RecyclerView.ViewHolder(uiBinding.root) {

    fun bindView(film: Film) {
        uiBinding.poster.loadImage(film.poster_path)
        uiBinding.votes.text = film.vote_average.toString()
        uiBinding.name.text = film.title
        uiBinding.description.text = film.overview
    }

    private fun ImageView.loadImage(url: String) {
        val compliteUrl = "https://image.tmdb.org/t/p/w200$url"
        Picasso.get().load(compliteUrl).into(this)
    }

}