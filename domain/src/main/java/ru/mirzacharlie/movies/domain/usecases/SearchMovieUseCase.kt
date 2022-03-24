package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.domain.models.MovieSearchParams
import ru.mirzacharlie.movies.domain.repository.MovieRepository

class SearchMovieUseCase(private val repository: MovieRepository) {

    suspend fun execute(params: MovieSearchParams) =
        repository.getByParams(params)
}