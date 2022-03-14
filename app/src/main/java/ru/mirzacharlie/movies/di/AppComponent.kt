package ru.mirzacharlie.movies.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.mirzacharlie.movies.App
import ru.mirzacharlie.movies.ui.activities.MainActivityComponent
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesFragmentComponent
import ru.mirzacharlie.movies.ui.screens.moviedetails.MovieDetailsFragmentComponent
import ru.mirzacharlie.movies.ui.screens.movies.MoviesFragmentComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance appContext: Application): AppComponent
    }

    fun inject(application: App)

    fun mainActivityComponent(): MainActivityComponent.Builder
    fun moviesComponent(): MoviesFragmentComponent.Builder
    fun movieDetailsComponent(): MovieDetailsFragmentComponent.Builder
    fun favouritesComponent(): FavouritesFragmentComponent.Builder
}
