package com.example.filmsapplication

import android.app.Application
import com.example.filmsapplication.di.AppComponent
import com.example.filmsapplication.di.DaggerAppComponent

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val component: AppComponent = initializeComponent()
        appComponent = component
    }


    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
        // return null
    }

    companion object {
        lateinit var appComponent: AppComponent
    }

}