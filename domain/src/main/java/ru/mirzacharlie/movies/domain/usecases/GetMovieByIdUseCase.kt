package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.domain.models.MovieModel
import ru.mirzacharlie.movies.domain.repository.MovieRepository

class GetMovieByIdUseCase(private val repository: MovieRepository) {

    suspend fun execute(id: Int): MovieModel = repository.getMovieById(id)
}