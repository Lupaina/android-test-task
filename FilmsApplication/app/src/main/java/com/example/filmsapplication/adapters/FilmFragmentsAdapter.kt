package com.example.filmsapplication.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.filmsapplication.ui.home.AllFilmsFragment
import com.example.filmsapplication.ui.home.FavouritesFilmsFragment

class FilmFragmentsAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = PAGES_COUNT

    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            FavouritesFilmsFragment.newInstance()
        } else {
            AllFilmsFragment.newInstance()
        }
    }

    companion object {
        private const val PAGES_COUNT = 2
    }
}