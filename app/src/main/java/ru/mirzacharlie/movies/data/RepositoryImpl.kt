package ru.mirzacharlie.movies.data

import ru.mirzacharlie.movies.PreferencesManager
import ru.mirzacharlie.movies.api.MovieDto
import ru.mirzacharlie.movies.api.MoviesRemoteDataSource

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
