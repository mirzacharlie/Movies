package ru.mirzacharlie.movies.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.mirzacharlie.movies.domain.models.MovieModel
import ru.mirzacharlie.movies.domain.repository.Repository

class GetMoviesUseCase(private val repository: Repository) {

    fun execute(): Flow<List<MovieModel>> = repository.movies
}