package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.domain.models.MovieDto
import ru.mirzacharlie.movies.domain.repository.Repository

class LoadMoviesPageUseCase(private val repository: Repository) {

    suspend fun execute(page: Int): List<MovieDto> =
        repository.loadMoviesPage(page)

}