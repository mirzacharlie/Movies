package ru.mirzacharlie.movies

import android.app.Application
import android.content.Context
import ru.mirzacharlie.movies.di.AppComponent
import ru.mirzacharlie.movies.di.AppInjector
import ru.mirzacharlie.movies.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppInjector.inject(this)
    }
}
