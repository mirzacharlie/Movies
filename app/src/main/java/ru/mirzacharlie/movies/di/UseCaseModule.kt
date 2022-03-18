package ru.mirzacharlie.movies.di

import org.koin.dsl.module

val useCaseModule = module {

    factory<ru.mirzacharlie.movies.domain.usecases.GetMoviesUseCase> {
        ru.mirzacharlie.movies.domain.usecases.GetMoviesUseCase(
            repository = get()
        )
    }
    factory<ru.mirzacharlie.movies.domain.usecases.GetFavouritesMoviesUseCase> {
        ru.mirzacharlie.movies.domain.usecases.GetFavouritesMoviesUseCase(
            repository = get()
        )
    }
    factory<ru.mirzacharlie.movies.domain.usecases.GetMovieByIdUseCase> {
        ru.mirzacharlie.movies.domain.usecases.GetMovieByIdUseCase(
            repository = get()
        )
    }
    factory<ru.mirzacharlie.movies.domain.usecases.SaveMoviesUseCase> {
        ru.mirzacharlie.movies.domain.usecases.SaveMoviesUseCase(
            repository = get()
        )
    }
    factory<ru.mirzacharlie.movies.domain.usecases.HasSavedMoviesUseCase> {
        ru.mirzacharlie.movies.domain.usecases.HasSavedMoviesUseCase(
            getMoviesUseCase = get()
        )
    }
    factory<ru.mirzacharlie.movies.domain.usecases.LoadMoviesPageUseCase> {
        ru.mirzacharlie.movies.domain.usecases.LoadMoviesPageUseCase(
            repository = get()
        )
    }
    factory<ru.mirzacharlie.movies.domain.usecases.AddMovieToFavouritesUseCase> {
        ru.mirzacharlie.movies.domain.usecases.AddMovieToFavouritesUseCase(
            repository = get()
        )
    }
    factory<ru.mirzacharlie.movies.domain.usecases.RemoveMovieFromFavouritesUseCase> {
        ru.mirzacharlie.movies.domain.usecases.RemoveMovieFromFavouritesUseCase(
            repository = get()
        )
    }
    factory<ru.mirzacharlie.movies.domain.usecases.LoadNewMoviesPageUseCase> {
        ru.mirzacharlie.movies.domain.usecases.LoadNewMoviesPageUseCase(
            loadMoviesPageUseCase = get(),
            saveMoviesUseCase = get(),
            repository = get()
        )
    }

}