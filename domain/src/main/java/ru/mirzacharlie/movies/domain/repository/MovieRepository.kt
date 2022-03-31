package ru.mirzacharlie.movies.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mirzacharlie.movies.domain.models.MovieModel
import ru.mirzacharlie.movies.domain.models.SearchParams


interface MovieRepository {

    val movies: Flow<List<MovieModel>>

    val favourites: Flow<List<MovieModel>>

    suspend fun loadMoviesPage(page: Int): List<MovieModel>

    suspend fun getMovieById(id: Int): MovieModel

    suspend fun saveMovies(movies: List<MovieModel>)

    suspend fun updateFavourite(id: Int, isFavourite: Boolean)

    suspend fun getByParams(params: List<SearchParams>): List<MovieModel>
}