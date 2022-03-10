package ru.mirzacharlie.movies

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.mirzacharlie.movies.di.AppInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector() = activityInjector

    override fun onCreate() {
        super.onCreate()

        AppInjector.inject(this)
    }
}
