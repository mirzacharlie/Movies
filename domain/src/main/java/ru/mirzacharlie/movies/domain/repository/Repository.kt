package ru.mirzacharlie.movies.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mirzacharlie.movies.domain.models.MovieDto
import ru.mirzacharlie.movies.domain.models.MovieEntity

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