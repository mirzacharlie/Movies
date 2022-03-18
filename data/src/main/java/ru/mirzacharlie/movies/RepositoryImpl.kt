package ru.mirzacharlie.movies

import ru.mirzacharlie.movies.api.MoviesRemoteDataSource
import ru.mirzacharlie.movies.data.MoviesLocalDataSource
import ru.mirzacharlie.movies.data.PreferencesManager
import ru.mirzacharlie.movies.domain.models.MovieDto
import ru.mirzacharlie.movies.domain.models.MovieEntity
import ru.mirzacharlie.movies.domain.repository.Repository

class RepositoryImpl (
    private val remoteDataSource: MoviesRemoteDataSource,
    private val localDataSource: MoviesLocalDataSource,
    private val preferencesManager: PreferencesManager
) : Repository {

    override val movies  = localDataSource.getPopulars()

    override val favourites = localDataSource.getFavourites()

    override suspend fun loadMoviesPage(page: Int): List<MovieDto> =
        remoteDataSource.getPopular(page = page).movies

    override suspend fun getMovieById(id: Int) =
        localDataSource.getById(id)

    override suspend fun saveMovies(movies: List<MovieEntity>) {
        localDataSource.insertList(movies)
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
