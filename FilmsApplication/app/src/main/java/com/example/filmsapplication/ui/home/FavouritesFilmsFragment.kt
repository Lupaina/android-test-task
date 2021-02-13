package com.example.filmsapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmsapplication.databinding.FragmentFavouritesFilmsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [FavouritesFilmsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouritesFilmsFragment : Fragment() {

    private lateinit var uiBinding: FragmentFavouritesFilmsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        uiBinding = FragmentFavouritesFilmsBinding.inflate(inflater, container, false)
        return uiBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavouritesFilmsFragment()
    }
}