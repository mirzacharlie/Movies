package ru.mirzacharlie.movies

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
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

    override suspend fun loadMoviesPage(page: Int): List<MovieModel> =
            remoteDataSource.getPopular(page = page).movies.map { it.toModel() }

    override suspend fun getMovieById(id: Int) =
            localDataSource.getById(id).toModel()

    override suspend fun saveMovies(movies: List<MovieModel>) {
            localDataSource.insertList(movies.map { it.toEntity() })
    }

    override suspend fun updateFavourite(id: Int, isFavourite: Int) {
            localDataSource.updateFavourite(id, isFavourite)
    }

    override fun getLastLoadedPageNumber(): Int =
        preferencesManager.getCashedPages()

    override fun setLastLoadedPageNumber(lastPage: Int) {
        preferencesManager.setCashedPages(lastPage)
    }
}
