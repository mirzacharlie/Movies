package ru.mirzacharlie.movies.data

import kotlinx.coroutines.flow.Flow
import ru.mirzacharlie.movies.api.MovieDto

interface Repository {

    val movies: Flow<List<MovieEntity>>

    val favourites: Flow<List<MovieEntity>>

    suspend fun loadMoviesPage(page: Int): List<MovieDto>

    suspend fun getMovieById(id: Int): MovieEntity

    suspend fun saveMovies(movies: List<MovieEntity>)

    suspend fun updateFavourite(id: Int, isFavourite: Int)

    fun getLastLoadedPageNumber(): Int

    fun setLastLoadedPageNumber(lastPage: Int)
}