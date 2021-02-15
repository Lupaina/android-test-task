package com.example.filmsapplication.di

import android.content.Context
import com.example.filmsapplication.ui.home.AllFilmsFragment
import com.example.filmsapplication.ui.home.FilmsViewModel
import com.example.filmsapplication.ui.home.HomeActivity
import com.example.filmsapplication.ui.login.LoginActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, RoomModule::class, AuthorizationModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(filmsViewModel: AllFilmsFragment)
    fun inject(filmsViewModel: HomeActivity)
    fun inject(filmsViewModel: FilmsViewModel)
    fun inject(loginActivity: LoginActivity)

}