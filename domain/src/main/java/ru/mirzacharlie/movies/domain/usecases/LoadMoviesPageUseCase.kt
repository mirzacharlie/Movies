package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.domain.models.MovieModel
import ru.mirzacharlie.movies.domain.repository.MovieRepository

class LoadMoviesPageUseCase(private val repository: MovieRepository) {

    suspend fun execute(page: Int): List<MovieModel> = repository.loadMoviesPage(page)
}