package com.example.filmsapplication.menagers

import android.os.Bundle
import android.util.Log
import com.example.filmsapplication.models.GraphObject
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.google.gson.Gson

class FBDataManager {

    private var accessToken = AccessToken.getCurrentAccessToken()
    private var avatarUrl: String? = null

    private fun updateAvatarUrl(accessToken: AccessToken?, callbackUrl: (url: String) -> Unit) {
        val callback = GraphRequest.GraphJSONObjectCallback { jsonObject, _ ->
            val graphObject = Gson().fromJson(jsonObject.toString(), GraphObject::class.java)
            val url = graphObject.picture.data.url
            avatarUrl = url
            callbackUrl(url)
        }
        GraphRequest.newMeRequest(accessToken, callback)
            .apply { parameters = Bundle().apply { putString("fields", "picture"); } }
            .executeAsync()
    }

    fun getAvatarUrl(callback: (url: String) -> Unit) {
        val avatarTmp = avatarUrl
        when {
            avatarTmp != null -> {
                callback(avatarTmp)
            }
            isLoggedIn() -> {
                updateAvatarUrl(accessToken, callback)
            }
            else -> {
                Log.i(TAG, "User not login")
            }
        }
    }

    fun isLoggedIn() = accessToken != null && !accessToken.isExpired

    fun refreshData() {
        accessToken = AccessToken.getCurrentAccessToken()
        avatarUrl = null
    }

    companion object {
        private const val TAG = "FBDataMenager"
    }
}