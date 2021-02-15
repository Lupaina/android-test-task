package com.example.filmsapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapplication.adapters.viewholders.FilmViewHolder
import com.example.filmsapplication.databinding.LayoutItemFilmBinding
import com.example.filmsapplication.models.Film
import com.example.filmsapplication.util.listener.FilmItemClickListener

class FavouriteFilmAdapter(
    private val itemClickListener: FilmItemClickListener
) : RecyclerView.Adapter<FilmViewHolder>() {

    private var list: MutableList<Film> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val uiBinding = LayoutItemFilmBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FilmViewHolder(uiBinding, itemClickListener)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = list[position]
        holder.bindView(film)
    }

    override fun getItemCount() = list.size

    fun getItemIfExist(position: Int): Film? {
        return list[position]
    }

    private fun setData(newList: List<Film>?) {
        if (!newList.isNullOrEmpty()) {
            list.addAll(newList)
        }
    }

    fun updateData(newList: List<Film>?) {
        list.clear()
        setData(newList)
        notifyDataSetChanged()
    }
}