package com.example.filmsapplication.data.retrofit.interceptors

import com.example.filmsapplication.menagers.AuthorizationManager
import com.example.filmsapplication.util.ApiConstants
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = AuthorizationManager.accessToken
        var request = chain.request()
        request = request.newBuilder()
            .addHeader(ApiConstants.AUTH_HEADER, token)
            .build()
        return chain.proceed(request)
    }

}