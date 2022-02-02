package ru.mirzacharlie.movies

import ru.mirzacharlie.movies.di.DaggerAppComponent
import ru.mirzacharlie.movies.ui.base.BaseApplication

class App : BaseApplication() {

    override fun onCreate() {

        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

        super.onCreate()
    }
}
