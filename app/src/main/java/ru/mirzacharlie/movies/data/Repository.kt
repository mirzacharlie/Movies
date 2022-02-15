package ru.mirzacharlie.movies.data

import ru.mirzacharlie.movies.PreferencesManager
import javax.inject.Inject

class Repository @Inject constructor(
    private val movieDao: MovieDao,
    private val preferencesManager: PreferencesManager
) {

    val movies = movieDao.getPopulars()
    val favourites = movieDao.getFavourites()

    suspend fun getMovieById(id: Int) =
        movieDao.getById(id)

    suspend fun saveMovies(movies: List<MovieEntity>) {
        movieDao.insertList(movies)
    }

    suspend fun updateFavourite(id: Int, isFavourite: Int) {
        movieDao.updateFavourite(id, isFavourite)
    }

    fun getLastLoadedPageNumber(): Int =
        preferencesManager.getCashedPages()

    fun setLastLoadedPageNumber(lastPage: Int) {
        preferencesManager.setCashedPages(lastPage)
    }
}
