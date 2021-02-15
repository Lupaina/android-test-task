package com.example.filmsapplication.di

import com.example.filmsapplication.menagers.FBDataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthorizationModule {
    @Singleton
    @Provides
    fun provideFBDataManager(): FBDataManager {
        return FBDataManager()
    }
}