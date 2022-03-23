package ru.mirzacharlie.movies.repositories

import kotlinx.coroutines.flow.map
import ru.mirzacharlie.movies.api.MovieRemoteDataSource
import ru.mirzacharlie.movies.data.MovieLocalDataSource
import ru.mirzacharlie.movies.domain.models.MovieModel
import ru.mirzacharlie.movies.domain.repository.MovieRepository
import ru.mirzacharlie.movies.models.toEntity
import ru.mirzacharlie.movies.models.toModel

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) : MovieRepository {

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

    override suspend fun updateFavourite(id: Int, isFavourite: Boolean) {
        localDataSource.updateFavourite(id, if (isFavourite) 1 else 0)
    }
}
