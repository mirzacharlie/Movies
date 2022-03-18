package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.api.MovieDto
import ru.mirzacharlie.movies.data.Repository

class LoadMoviesPageUseCase(private val repository: Repository) {

    suspend fun execute(page: Int): List<MovieDto> =
        repository.loadMoviesPage(page)

}