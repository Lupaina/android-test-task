package com.example.filmsapplication.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.filmsapplication.BaseApplication
import com.example.filmsapplication.databinding.ActivityLoginBinding
import com.example.filmsapplication.menagers.FBDataManager
import com.example.filmsapplication.ui.home.HomeActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    private lateinit var uiBinding: ActivityLoginBinding
    private lateinit var callbackManager: CallbackManager

    @Inject
    lateinit var fbDataManager: FBDataManager

    init {
        BaseApplication.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(uiBinding.root)
        checkLogin()
        initUI()
        initFbLoginCallback()
    }

    private fun checkLogin() {
        if (fbDataManager.isLoggedIn()) {
            toHomeActivity()
            finish()
        }
    }

    private fun initUI() {
        uiBinding.skipButton.setOnClickListener {
            toHomeActivity()
        }
        uiBinding.loginButton.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile"))
        }
    }

    private fun initFbLoginCallback() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    fbDataManager.refreshData()
                    toHomeActivity()
                    finish()
                }

                override fun onCancel() {
                    Log.i(TAG, "onCancel")
                }

                override fun onError(exception: FacebookException) {
                    Log.e(TAG, "onError", exception)
                }
            })
    }

    private fun toHomeActivity() {
        val intent = HomeActivity.getStartIntent(this)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}