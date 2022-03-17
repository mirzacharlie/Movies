package ru.mirzacharlie.movies.data

import androidx.lifecycle.LiveData
import ru.mirzacharlie.movies.PreferencesManager

class RepositoryImpl (
    private val movieDao: MovieDao,
    private val preferencesManager: PreferencesManager
) : Repository {

    override fun getMovies(): LiveData<List<MovieEntity>> = movieDao.getPopulars()

    override fun getFavourites(): LiveData<List<MovieEntity>> = movieDao.getFavourites()

    override suspend fun getMovieById(id: Int) =
        movieDao.getById(id)

    override suspend fun saveMovies(movies: List<MovieEntity>) {
        movieDao.insertList(movies)
    }

    override suspend fun updateFavourite(id: Int, isFavourite: Int) {
        movieDao.updateFavourite(id, isFavourite)
    }

    override fun getLastLoadedPageNumber(): Int =
        preferencesManager.getCashedPages()

    override fun setLastLoadedPageNumber(lastPage: Int) {
        preferencesManager.setCashedPages(lastPage)
    }
}
