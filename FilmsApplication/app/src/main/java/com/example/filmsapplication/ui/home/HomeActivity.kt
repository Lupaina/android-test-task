package com.example.filmsapplication.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.filmsapplication.BaseApplication
import com.example.filmsapplication.R
import com.example.filmsapplication.adapters.FilmFragmentsAdapter
import com.example.filmsapplication.databinding.ActivityHomeBinding
import com.example.filmsapplication.menagers.FBDataManager
import com.example.filmsapplication.util.extansions.loadImageByUrl
import com.facebook.login.LoginManager
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private lateinit var uiBinding: ActivityHomeBinding

    @Inject
    lateinit var fbDataManager: FBDataManager

    init {
        BaseApplication.appComponent.inject(this)
    }

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

        initAuthConfiguration()
        uiBinding.logout.setOnClickListener {
            LoginManager.getInstance().logOut()
            fbDataManager.refreshData()
            finish()
        }
    }

    private fun initAuthConfiguration() {
        val isLogin = fbDataManager.isLoggedIn()
        uiBinding.logout.isVisible = isLogin
        uiBinding.avatar.isVisible = isLogin

        if (isLogin) {
            loadUserAvatar()
        } else {
            setDefaultAvatar()
        }
    }

    private fun loadUserAvatar() {
        fbDataManager.getAvatarUrl { url ->
            uiBinding.avatar.loadImageByUrl(url)
        }
    }

    private fun setDefaultAvatar() {
        uiBinding.avatar.setImageResource(R.drawable.ic_account_circle)
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}