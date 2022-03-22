package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.domain.models.Page
import ru.mirzacharlie.movies.domain.repository.PageRepository

class LoadNewMoviesPageUseCase(
    private val loadMoviesPageUseCase: LoadMoviesPageUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase,
    private val repository: PageRepository
) {

    suspend fun execute() {
        val lastLoadedPage = repository.getLastLoadedPageNumber().value
        val newPage = loadMoviesPageUseCase.execute(lastLoadedPage + 1)
        saveMoviesUseCase.execute(newPage)
        repository.setLastLoadedPageNumber(Page(lastLoadedPage + 1))
    }
}