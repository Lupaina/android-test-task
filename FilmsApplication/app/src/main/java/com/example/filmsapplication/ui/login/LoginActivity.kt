package com.example.filmsapplication.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.filmsapplication.databinding.ActivityLoginBinding
import com.example.filmsapplication.ui.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var uiBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(uiBinding.root)
        initUI()
    }

    private fun initUI() {
        uiBinding.skipButton.setOnClickListener {
            val intent = HomeActivity.getStartIntent(this)
            startActivity(intent)
        }
        uiBinding.loginButton.setOnClickListener {
            // todo add Facebook login
        }
    }
}