package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.domain.models.MovieEntity
import ru.mirzacharlie.movies.domain.repository.Repository

class SaveMoviesUseCase(private val repository: Repository) {

    suspend fun execute(movies: List<MovieEntity>) {
        repository.saveMovies(movies)
    }
}