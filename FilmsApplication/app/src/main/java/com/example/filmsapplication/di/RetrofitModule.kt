package com.example.filmsapplication.di

import android.util.Log
import com.example.filmsapplication.data.retrofit.ApiService
import com.example.filmsapplication.data.retrofit.interceptors.AuthorizationInterceptor
import com.example.filmsapplication.data.room.FilmsDao
import com.example.filmsapplication.repository.FilmRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/discover/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
        val loggingInterceptor = HttpLoggingInterceptor { message: String? ->
            Log.i("OkHttp", message ?: "")
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(loggingInterceptor)
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit.Builder): ApiService {
        return retrofit.build().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideFilmRepository(apiService: ApiService, filmsDao: FilmsDao): FilmRepository {
        return FilmRepository(apiService, filmsDao)
    }
}