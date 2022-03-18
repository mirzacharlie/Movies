package ru.mirzacharlie.movies

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.mirzacharlie.movies.di.appModule
import ru.mirzacharlie.movies.di.dataModule
import ru.mirzacharlie.movies.di.networkModule
import ru.mirzacharlie.movies.di.useCaseModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appModule, networkModule, dataModule, useCaseModule))
        }
    }
}
