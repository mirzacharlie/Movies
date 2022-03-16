package ru.mirzacharlie.movies.di

import android.app.Activity
import android.app.Application
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import ru.mirzacharlie.movies.App
import ru.mirzacharlie.movies.ui.activities.MainActivity
import ru.mirzacharlie.movies.ui.activities.MainActivityComponent
import ru.mirzacharlie.movies.ui.base.BaseFragment
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesFragment
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesFragmentComponent
import ru.mirzacharlie.movies.ui.screens.moviedetails.MovieDetailsFragment
import ru.mirzacharlie.movies.ui.screens.moviedetails.MovieDetailsFragmentComponent
import ru.mirzacharlie.movies.ui.screens.movies.MoviesFragment
import ru.mirzacharlie.movies.ui.screens.movies.MoviesFragmentComponent
import kotlin.reflect.KClass

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

    fun createComponent(activity: AppCompatActivity, component: ActivityComponent?) {
        activityComponents[activity] = component
    }

    fun createComponent(fragment: Fragment, component: FragmentComponent?) {
        fragmentComponents[fragment] = component
    }

    fun destroyComponent(activity: AppCompatActivity) {
        activityComponents.remove(activity)
    }

    fun destroyComponent(fragment: Fragment) {
        fragmentComponents.remove(fragment)
    }
}
