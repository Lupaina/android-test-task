package com.example.filmsapplication.di

import android.content.Context
import androidx.room.Room
import com.example.filmsapplication.data.room.AppDataBase
import com.example.filmsapplication.data.room.FilmsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDateBase(context: Context): AppDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            AppDataBase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideFilmsDao(database: AppDataBase): FilmsDao {
        return database.filmDao()
    }
}