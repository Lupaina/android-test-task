package com.example.filmsapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.filmsapplication.adapters.FavouriteFilmAdapter
import com.example.filmsapplication.databinding.FragmentFavouritesFilmsBinding
import com.example.filmsapplication.util.extansions.shareFilm
import com.example.filmsapplication.util.listener.FilmItemClickListener

class FavouritesFilmsFragment : Fragment() {

    private lateinit var uiBinding: FragmentFavouritesFilmsBinding
    private val viewModel: FilmsViewModel by activityViewModels()
    private lateinit var filmAdapter: FavouriteFilmAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        uiBinding = FragmentFavouritesFilmsBinding.inflate(inflater, container, false)
        return uiBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObservers()
    }

    private fun initUI() {
        filmAdapter = FavouriteFilmAdapter(object : FilmItemClickListener {
            override fun like(position: Int) {
                val item = filmAdapter.getItemIfExist(position)?.apply {
                    isFavorite = false
                }
                item?.let {
                    viewModel.updateFavoriteList(it)
                    viewModel.updateItemInMainList(it)
                }
            }

            override fun share(position: Int) {
                val itemId = filmAdapter.getItemIfExist(position)?.id
                itemId?.let { shareFilm(it) }
            }
        })
        uiBinding.recyclerViewFilms.adapter = filmAdapter
    }

    private fun initObservers() {
        viewModel.favouriteFilmsList.observe(viewLifecycleOwner, {
            uiBinding.noData.isVisible = it.isEmpty()
            filmAdapter.updateData(it)
        })
    }
    companion object {
        @JvmStatic
        fun newInstance() = FavouritesFilmsFragment()
    }
}