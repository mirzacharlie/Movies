package ru.mirzacharlie.movies

import android.app.Application
import android.content.Context
import ru.mirzacharlie.movies.di.AppComponent
import ru.mirzacharlie.movies.di.AppInjector
import ru.mirzacharlie.movies.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        AppInjector.inject(this)
    }
}

//val Context.appComponent: AppComponent
//    get() = when (this) {
//        is App -> appComponent
//        else -> applicationContext.appComponent
//    }
