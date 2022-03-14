package ru.mirzacharlie.movies.ui.screens.moviedetails

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import ru.mirzacharlie.movies.di.ViewModelKey
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesFragment
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesVM
import javax.inject.Scope

@Scope
annotation class MovieDetailsFragmentScope

@Module
interface MovieDetailsModule {

    @Binds @IntoMap @ViewModelKey(MovieDetailsVM::class)
    fun provideViewModel(viewModel: MovieDetailsVM): ViewModel
}

@Subcomponent(modules = [MovieDetailsModule::class])
@MovieDetailsFragmentScope
interface MovieDetailsFragmentComponent {

    fun inject(fragment: MovieDetailsFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): MovieDetailsFragmentComponent
    }
}
