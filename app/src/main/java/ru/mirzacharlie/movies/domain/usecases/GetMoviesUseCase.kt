package ru.mirzacharlie.movies.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.mirzacharlie.movies.data.MovieEntity
import ru.mirzacharlie.movies.data.Repository

class GetMoviesUseCase(private val repository: Repository) {

    fun execute(): Flow<List<MovieEntity>> = repository.movies

}