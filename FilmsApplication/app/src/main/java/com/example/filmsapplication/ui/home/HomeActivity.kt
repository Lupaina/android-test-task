package com.example.filmsapplication.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmsapplication.BaseApplication
import com.example.filmsapplication.R
import com.example.filmsapplication.adapters.FilmFragmentsAdapter
import com.example.filmsapplication.databinding.ActivityHomeBinding
import com.example.filmsapplication.repository.FilmRepository
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private lateinit var uiBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(uiBinding.root)
        initUI()

    }

    private fun initUI() {
        val pagerAdapter = FilmFragmentsAdapter(this)
        uiBinding.filmsViewPager.adapter = pagerAdapter
        TabLayoutMediator(uiBinding.filmsTabLayout, uiBinding.filmsViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.films_title)
                1 -> tab.text = getString(R.string.favourites_title)
            }
        }.attach()
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}