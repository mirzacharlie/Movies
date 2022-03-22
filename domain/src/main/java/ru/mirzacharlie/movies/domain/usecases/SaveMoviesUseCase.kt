package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.domain.models.MovieModel
import ru.mirzacharlie.movies.domain.repository.MovieRepository

class SaveMoviesUseCase(private val repository: MovieRepository) {

    suspend fun execute(movies: List<MovieModel>) {
        repository.saveMovies(movies)
    }
}