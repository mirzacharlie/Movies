package ru.mirzacharlie.movies.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.mirzacharlie.movies.App

object AppInjector {

    private val TAG = "AppInjector"

    private var app: App? = null

    var appComponent: AppComponent? = null

    private val activityComponents = mutableMapOf<AppCompatActivity, ActivityComponent?>()
    private val fragmentComponents = mutableMapOf<Fragment, FragmentComponent?>()

    fun inject(app: App) {

        this.app = app

        appComponent = DaggerAppComponent
            .factory()
            .create(app)

        appComponent?.inject(app)
    }

    fun holdComponent(activity: AppCompatActivity, component: ActivityComponent?) {
        activityComponents[activity] = component
    }

    fun holdComponent(fragment: Fragment, component: FragmentComponent?) {
        fragmentComponents[fragment] = component
    }

    fun unholdComponent(activity: AppCompatActivity) {
        activityComponents.remove(activity)
    }

    fun unholdComponent(fragment: Fragment) {
        fragmentComponents.remove(fragment)
    }
}
