package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.domain.repository.Repository

class RemoveMovieFromFavouritesUseCase(private val repository: Repository) {

    suspend fun execute(id: Int) {
        repository.updateFavourite(id, false)
    }
}