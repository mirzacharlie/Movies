package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.data.Repository

class AddMovieToFavouritesUseCase(private val repository: Repository) {

    suspend fun execute(id: Int) {
        repository.updateFavourite(id, 1)
    }
}