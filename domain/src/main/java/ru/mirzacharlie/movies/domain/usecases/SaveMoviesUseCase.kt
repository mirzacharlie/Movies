package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.domain.models.MovieModel
import ru.mirzacharlie.movies.domain.repository.Repository

class SaveMoviesUseCase(private val repository: Repository) {

    fun execute(movies: List<MovieModel>) {
        repository.saveMovies(movies)
    }
}