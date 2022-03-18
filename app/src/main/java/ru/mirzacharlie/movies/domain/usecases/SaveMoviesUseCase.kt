package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.data.MovieEntity
import ru.mirzacharlie.movies.data.Repository

class SaveMoviesUseCase(private val repository: Repository) {

    suspend fun execute(movies: List<MovieEntity>) {
        repository.saveMovies(movies)
    }
}