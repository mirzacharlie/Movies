package ru.mirzacharlie.movies.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.mirzacharlie.movies.domain.models.MovieModel
import ru.mirzacharlie.movies.domain.repository.MovieRepository

class GetFavouritesMoviesUseCase(private val repository: MovieRepository) {

    fun execute(): Flow<List<MovieModel>> = repository.favourites
}