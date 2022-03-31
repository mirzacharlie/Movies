package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.domain.models.SearchParams
import ru.mirzacharlie.movies.domain.repository.MovieRepository

class SearchMovieUseCase(private val repository: MovieRepository) {

    suspend fun execute(params: List<SearchParams>) =
        repository.getByParams(params)
}