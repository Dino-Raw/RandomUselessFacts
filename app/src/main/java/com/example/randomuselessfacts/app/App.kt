package com.example.randomuselessfacts.app

import android.app.Application
import android.content.Context
import com.example.randomuselessfacts.di.AppComponent
import com.example.randomuselessfacts.di.AppModule
import com.example.randomuselessfacts.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }
}