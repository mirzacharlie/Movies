package ru.mirzacharlie.movies.domain.usecases

import ru.mirzacharlie.movies.domain.repository.Repository

class LoadNewMoviesPageUseCase(
    private val loadMoviesPageUseCase: LoadMoviesPageUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase,
    private val repository: Repository
) {

    suspend fun execute() {
        val lastLoadedPage = repository.getLastLoadedPageNumber()
        val newPage = loadMoviesPageUseCase.execute(lastLoadedPage + 1)
        saveMoviesUseCase.execute(newPage)
        repository.setLastLoadedPageNumber(lastLoadedPage + 1)
    }
}