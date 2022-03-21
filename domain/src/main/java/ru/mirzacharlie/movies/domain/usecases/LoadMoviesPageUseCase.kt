package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.domain.models.MovieModel
import ru.mirzacharlie.movies.domain.repository.Repository

class LoadMoviesPageUseCase(private val repository: Repository) {

    suspend fun execute(page: Int): List<MovieModel> = repository.loadMoviesPage(page)
}