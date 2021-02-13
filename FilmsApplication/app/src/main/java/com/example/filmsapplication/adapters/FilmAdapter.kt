package com.example.filmsapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.example.filmsapplication.adapters.diff.FilmsDiffCallback
import com.example.filmsapplication.adapters.viewholders.FilmViewHolder
import com.example.filmsapplication.databinding.LayoutItemFilmBinding
import com.example.filmsapplication.models.Film

class FilmAdapter() : PagedListAdapter<Film, FilmViewHolder>(FilmsDiffCallback) {

    // private var list: MutableList<Film> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val uiBinding = LayoutItemFilmBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FilmViewHolder(uiBinding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = getItem(position)
        film?.let {
            holder.bindView(it)
        }
        // val film = list[position]
    }

    /*private fun setData(newList: List<Film>?) {
        if (!newList.isNullOrEmpty()) {
            list.addAll(newList)
        }
    }

    fun appendData(newList: List<Film>?) {
        setData(newList)
        notifyDataSetChanged()
    }

    fun updateData(newList: List<Film>?) {
        list.clear()
        appendData(newList)
    }*/

}