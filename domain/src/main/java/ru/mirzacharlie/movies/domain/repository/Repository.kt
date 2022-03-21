package ru.mirzacharlie.movies.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mirzacharlie.movies.domain.models.MovieModel


interface Repository {

    val movies: Flow<List<MovieModel>>

    val favourites: Flow<List<MovieModel>>

    fun loadMoviesPage(page: Int): List<MovieModel>

    fun getMovieById(id: Int): MovieModel

    fun saveMovies(movies: List<MovieModel>)

    fun updateFavourite(id: Int, isFavourite: Int)

    fun getLastLoadedPageNumber(): Int

    fun setLastLoadedPageNumber(lastPage: Int)
}