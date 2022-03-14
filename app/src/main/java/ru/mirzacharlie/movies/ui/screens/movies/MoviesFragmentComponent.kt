package ru.mirzacharlie.movies.ui.screens.movies

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import ru.mirzacharlie.movies.di.ViewModelKey
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesFragment
import ru.mirzacharlie.movies.ui.screens.moviedetails.MovieDetailsFragmentComponent
import ru.mirzacharlie.movies.ui.screens.moviedetails.MovieDetailsVM
import javax.inject.Scope

@Scope
annotation class MoviesFragmentScope

@Module
interface MoviesModule {

    @Binds @IntoMap @ViewModelKey(MoviesVM::class)
    fun provideViewModel(viewModel: MoviesVM): ViewModel
}

@Subcomponent(modules = [MoviesModule::class])
@MoviesFragmentScope
interface MoviesFragmentComponent {

    fun inject(fragment: MoviesFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): MoviesFragmentComponent
    }
}