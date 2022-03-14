package ru.mirzacharlie.movies.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import ru.mirzacharlie.movies.App
import ru.mirzacharlie.movies.ui.activities.MainActivity
import ru.mirzacharlie.movies.ui.activities.MainActivityComponent
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesFragment
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesFragmentComponent
import ru.mirzacharlie.movies.ui.screens.moviedetails.MovieDetailsFragment
import ru.mirzacharlie.movies.ui.screens.moviedetails.MovieDetailsFragmentComponent
import ru.mirzacharlie.movies.ui.screens.movies.MoviesFragment
import ru.mirzacharlie.movies.ui.screens.movies.MoviesFragmentComponent

object AppInjector {

    val TAG = "AppInjector"

    var app: App? = null

    var appComponent: AppComponent? = null

    var mainActivityComponent: MainActivityComponent? = null
    var moviesComponent: MoviesFragmentComponent? = null
    var movieDetailsComponent: MovieDetailsFragmentComponent? = null
    var favouritesComponent: FavouritesFragmentComponent? = null

    fun inject(app: App) {

        this.app = app

        appComponent = DaggerAppComponent
            .factory()
            .create(app)

        appComponent?.inject(app)

        registerActivityLifecycleCallbacks()
    }

    private fun registerActivityLifecycleCallbacks() {

        app?.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {

            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                handleActivity(activity)
            }

            override fun onActivityDestroyed(activity: Activity) {
                destroyActivityComponent(activity)
            }

            override fun onActivityStarted(p0: Activity) {}
            override fun onActivityResumed(p0: Activity) {}
            override fun onActivityPaused(p0: Activity) {}
            override fun onActivityStopped(p0: Activity) {}
            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}
        })
    }

    private fun handleActivity(activity: Activity) {

        createActivityComponent(activity)

        if (activity is FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {

                    override fun onFragmentCreated(
                        fm: FragmentManager,
                        f: Fragment,
                        savedInstanceState: Bundle?
                    ) {
                        createFragmentComponent(f)
                    }

                    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
                        super.onFragmentDetached(fm, f)
                        destroyFragmentComponent(f)
                    }
                },
                true
            )
        }
    }

    private fun createActivityComponent(activity: Activity) {
        when (activity) {
            is MainActivity -> {
                createMainActivityComponent()
                mainActivityComponent?.inject(activity)

                Log.d(TAG, "mainActivityComponent == $mainActivityComponent")
            }
        }
    }

    private fun destroyActivityComponent(activity: Activity) {
        when (activity) {
            is MainActivity -> {
                mainActivityComponent = null
                Log.d(TAG, "mainActivityComponent == $mainActivityComponent")
            }
        }
    }

    private fun createFragmentComponent(fragment: Fragment) {
        when (fragment) {
            is MoviesFragment -> {
                createMoviesComponent()
                moviesComponent?.inject(fragment)
                Log.d(TAG, "moviesFragmentComponent == $moviesComponent")
            }
            is MovieDetailsFragment -> {
                createMovieDetailsComponent()
                movieDetailsComponent?.inject(fragment)
                Log.d(TAG, "movieDetailComponent == $movieDetailsComponent")
            }
            is FavouritesFragment -> {
                createFavouritesComponent()
                favouritesComponent?.inject(fragment)
                Log.d(TAG, "favFragmentComponent == $favouritesComponent")
            }
        }
    }

    private fun destroyFragmentComponent(fragment: Fragment) {
        when (fragment) {
            is MoviesFragment ->{
                moviesComponent = null
                Log.d(TAG, "moviesFragmentComponent == $moviesComponent")
            }
            is MovieDetailsFragment -> {
                movieDetailsComponent = null
                Log.d(TAG, "movieDetailComponent == $movieDetailsComponent")
            }
            is FavouritesFragment -> {
                favouritesComponent = null
                Log.d(TAG, "favFragmentComponent == $favouritesComponent")
            }
        }
    }

    private fun createMainActivityComponent() {
        if (mainActivityComponent == null) {
            mainActivityComponent = appComponent?.mainActivityComponent()?.build()
        }
    }

    private fun createMoviesComponent() {
        if (moviesComponent == null)
            moviesComponent = appComponent?.moviesComponent()?.build()
    }

    private fun createMovieDetailsComponent() {
        if (movieDetailsComponent == null)
            movieDetailsComponent = appComponent?.movieDetailsComponent()?.build()
    }

    private fun createFavouritesComponent() {
        if (favouritesComponent == null)
            favouritesComponent = appComponent?.favouritesComponent()?.build()
    }
}
