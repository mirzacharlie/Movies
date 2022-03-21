package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.domain.models.MovieModel
import ru.mirzacharlie.movies.domain.repository.Repository

class GetMovieByIdUseCase(private val repository: Repository) {

    suspend fun execute(id: Int): MovieModel = repository.getMovieById(id)
}