package ru.mirzacharlie.movies.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import ru.mirzacharlie.movies.App
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesFragment
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesFragmentComponent
import ru.mirzacharlie.movies.ui.screens.moviedetails.MovieDetailsFragment
import ru.mirzacharlie.movies.ui.screens.moviedetails.MovieDetailsFragmentComponent
import ru.mirzacharlie.movies.ui.screens.movies.MoviesFragment
import ru.mirzacharlie.movies.ui.screens.movies.MoviesFragmentComponent

//object AppInjector {
//
//    var app: App? = null
//
//    var appComponent: AppComponent? = null
//    var moviesComponent: MoviesFragmentComponent? = null
//    var movieDetailsComponent: MovieDetailsFragmentComponent? = null
//    var favouritesComponent: FavouritesFragmentComponent? = null
//
//    fun inject(app: App) {
//
//        this.app = app
//
//        appComponent = DaggerAppComponent.builder()
//            .context(app)
//            .build()
//
//        appComponent?.inject(app)
//
//        registerActivityLifecycleCallbacks()
//    }
//
//    private fun registerActivityLifecycleCallbacks() {
//
//        app?.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
//
//            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
//                handleActivity(activity)
//            }
//
//            override fun onActivityStarted(p0: Activity) {}
//            override fun onActivityResumed(p0: Activity) {}
//            override fun onActivityPaused(p0: Activity) {}
//            override fun onActivityStopped(p0: Activity) {}
//            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}
//            override fun onActivityDestroyed(p0: Activity) {}
//        })
//    }
//
//    private fun handleActivity(activity: Activity) {
//
//        AndroidInjection.inject(activity)
//
//        if (activity is FragmentActivity) {
//            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
//                object : FragmentManager.FragmentLifecycleCallbacks() {
//                    override fun onFragmentCreated(
//                        fm: FragmentManager,
//                        f: Fragment,
//                        savedInstanceState: Bundle?
//                    ) {
//                        when(f) {
//                            is MoviesFragment -> {
//                                createMoviesComponent()
//                                moviesComponent?.inject(f)
//                            }
//                            is MovieDetailsFragment -> {
//                                createMovieDetailsComponent()
//                                movieDetailsComponent?.inject(f)
//                            }
//                            is FavouritesFragment -> {
//                                createFavouritesComponent()
//                                favouritesComponent?.inject(f)
//                            }
//                        }
//                    }
//                },
//                true
//            )
//        }
//    }
//
//    private fun createMoviesComponent() {
//        if (moviesComponent == null)
//            moviesComponent = appComponent?.moviesComponent()?.build()
//    }
//
//    private fun createMovieDetailsComponent() {
//        if (movieDetailsComponent == null)
//            movieDetailsComponent = appComponent?.movieDetailsComponent()?.build()
//    }
//
//    private fun createFavouritesComponent() {
//        if (favouritesComponent == null)
//            favouritesComponent = appComponent?.favouritesComponent()?.build()
//    }
//}
