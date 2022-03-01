package ru.mirzacharlie.movies.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.mirzacharlie.movies.ui.activities.MainVM
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesVM
import ru.mirzacharlie.movies.ui.screens.moviedetails.MovieDetailsVM
import ru.mirzacharlie.movies.ui.screens.movies.MoviesVM

val appModule = module {

    viewModel<MainVM> {
        MainVM()
    }

    viewModel<MoviesVM> {
        MoviesVM(
            apiService = get(),
            repository = get()
        )
    }

    viewModel<MovieDetailsVM> {
        MovieDetailsVM(repository = get())
    }

    viewModel<FavouritesVM> {
        FavouritesVM(repository = get())
    }
}
