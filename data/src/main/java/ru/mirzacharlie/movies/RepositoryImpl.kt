package ru.mirzacharlie.movies

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.mirzacharlie.movies.api.MoviesRemoteDataSource
import ru.mirzacharlie.movies.data.MoviesLocalDataSource
import ru.mirzacharlie.movies.data.PreferencesManager
import ru.mirzacharlie.movies.domain.models.MovieModel
import ru.mirzacharlie.movies.domain.repository.Repository
import ru.mirzacharlie.movies.models.toEntity
import ru.mirzacharlie.movies.models.toModel
import kotlin.coroutines.CoroutineContext

class RepositoryImpl(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val localDataSource: MoviesLocalDataSource,
    private val preferencesManager: PreferencesManager
) : Repository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override val movies = localDataSource.getPopulars()
        .map { it.map { e -> e.toModel() } }

    override val favourites = localDataSource.getFavourites()
        .map { it.map { e -> e.toModel() } }

    override fun loadMoviesPage(page: Int): List<MovieModel> =
        runBlocking(coroutineContext) {
            remoteDataSource.getPopular(page = page).movies.map { it.toModel() }
        }

    override fun getMovieById(id: Int) =
        runBlocking(coroutineContext) {
            localDataSource.getById(id).toModel()
        }

    override fun saveMovies(movies: List<MovieModel>) {
        launch(coroutineContext) {
            localDataSource.insertList(movies.map { it.toEntity() })
        }
    }

    override fun updateFavourite(id: Int, isFavourite: Int) {
        launch(coroutineContext) {
            localDataSource.updateFavourite(id, isFavourite)
        }
    }

    override fun getLastLoadedPageNumber(): Int =
        preferencesManager.getCashedPages()

    override fun setLastLoadedPageNumber(lastPage: Int) {
        preferencesManager.setCashedPages(lastPage)
    }
}
