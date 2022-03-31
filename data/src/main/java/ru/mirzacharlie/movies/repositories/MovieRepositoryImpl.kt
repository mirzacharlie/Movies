package ru.mirzacharlie.movies.repositories

import androidx.sqlite.db.SimpleSQLiteQuery
import kotlinx.coroutines.flow.map
import ru.mirzacharlie.movies.api.MovieRemoteDataSource
import ru.mirzacharlie.movies.data.MovieLocalDataSource
import ru.mirzacharlie.movies.domain.models.MovieModel
import ru.mirzacharlie.movies.domain.models.SearchParams
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

    override suspend fun getByParams(params: List<SearchParams>): List<MovieModel> {
        val query = StringBuilder("SELECT * FROM movies WHERE")

        params.forEachIndexed { index, param ->
            val part = param.query()

            if (part.isNotEmpty()) {

                query.append(" ")
                query.append(part)

                if (index != params.size - 1) {
                    query.append(" and")
                }
            }
        }

        return localDataSource.rawQuery(SimpleSQLiteQuery(query.toString())).map { it.toModel() }
    }

    override suspend fun saveMovies(movies: List<MovieModel>) {
        localDataSource.insertList(movies.map { it.toEntity() })
    }

    override suspend fun updateFavourite(id: Int, isFavourite: Boolean) {
        localDataSource.updateFavourite(id, if (isFavourite) 1 else 0)
    }
}
