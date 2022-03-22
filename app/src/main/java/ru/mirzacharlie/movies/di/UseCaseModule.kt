package ru.mirzacharlie.movies.di

import org.koin.dsl.module
import ru.mirzacharlie.movies.domain.usecases.*

val useCaseModule = module {

    factory<GetMoviesUseCase> { GetMoviesUseCase(repository = get()) }
    factory<GetFavouritesMoviesUseCase> { GetFavouritesMoviesUseCase(repository = get()) }
    factory<GetMovieByIdUseCase> { GetMovieByIdUseCase(repository = get()) }
    factory<SaveMoviesUseCase> { SaveMoviesUseCase(repository = get()) }
    factory<HasSavedMoviesUseCase> { HasSavedMoviesUseCase(getMoviesUseCase = get()) }
    factory<LoadMoviesPageUseCase> { LoadMoviesPageUseCase(repository = get()) }
    factory<AddMovieToFavouritesUseCase> { AddMovieToFavouritesUseCase(repository = get()) }
    factory<RemoveMovieFromFavouritesUseCase> { RemoveMovieFromFavouritesUseCase(repository = get()) }
    factory<LoadNewMoviesPageUseCase> {
        LoadNewMoviesPageUseCase(
            loadMoviesPageUseCase = get(),
            saveMoviesUseCase = get(),
            repository = get()
        )
    }

}