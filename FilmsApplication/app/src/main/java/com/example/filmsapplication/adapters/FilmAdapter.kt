package com.example.filmsapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.example.filmsapplication.adapters.diff.FilmsDiffCallback
import com.example.filmsapplication.adapters.viewholders.FilmViewHolder
import com.example.filmsapplication.databinding.LayoutItemFilmBinding
import com.example.filmsapplication.models.Film
import com.example.filmsapplication.util.listener.FilmItemClickListener

class FilmAdapter(private val itemClickListener: FilmItemClickListener) :
    PagedListAdapter<Film, FilmViewHolder>(FilmsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val uiBinding = LayoutItemFilmBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FilmViewHolder(uiBinding,itemClickListener)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = getItem(position)
        film?.let { holder.bindView(it)}
    }

    fun getItemIfExist(position: Int):Film?{
        return getItem(position)
    }

}