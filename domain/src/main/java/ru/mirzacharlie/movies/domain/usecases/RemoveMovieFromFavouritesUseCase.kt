package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.domain.repository.MovieRepository

class RemoveMovieFromFavouritesUseCase(private val repository: MovieRepository) {

    suspend fun execute(id: Int) {
        repository.updateFavourite(id, false)
    }
}