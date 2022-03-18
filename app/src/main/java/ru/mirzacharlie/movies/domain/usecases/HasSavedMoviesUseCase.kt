package ru.mirzacharlie.movies.domain.usecases

import kotlinx.coroutines.flow.firstOrNull

class HasSavedMoviesUseCase(private val getMoviesUseCase: GetMoviesUseCase) {

    suspend fun execute(): Boolean {
        val movies = getMoviesUseCase.execute().firstOrNull()
        return !movies.isNullOrEmpty()
    }

}