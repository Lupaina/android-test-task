package com.example.filmsapplication.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapplication.R
import com.example.filmsapplication.databinding.LayoutItemFilmBinding
import com.example.filmsapplication.models.Film
import com.example.filmsapplication.util.extansions.loadImageByUrl
import com.example.filmsapplication.util.listener.FilmItemClickListener

class FilmViewHolder(
    private val uiBinding: LayoutItemFilmBinding,
    private val itemClickListener: FilmItemClickListener
) :
    RecyclerView.ViewHolder(uiBinding.root) {

    init {
        uiBinding.likeButton.setOnClickListener { itemClickListener.like(adapterPosition) }
        uiBinding.shareButton.setOnClickListener { itemClickListener.share(adapterPosition) }
    }

    fun bindView(film: Film) {
        val fullUrl = "https://image.tmdb.org/t/p/w200${film.backdropPath}"
        uiBinding.poster.loadImageByUrl(fullUrl)
        uiBinding.votes.text = film.vote_average.toString()
        uiBinding.name.text = film.title
        uiBinding.description.text = film.overview
        val buttonTitle = if (film.isFavorite) R.string.remove_button else R.string.like_button
        uiBinding.likeButton.setText(buttonTitle)
    }
}