package com.example.filmsapplication.adapters.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.filmsapplication.models.Film

object FilmsDiffCallback : DiffUtil.ItemCallback<Film>() {

    override fun areItemsTheSame(oldItem: Film, newItem: Film) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Film, newItem: Film) = oldItem == newItem
}