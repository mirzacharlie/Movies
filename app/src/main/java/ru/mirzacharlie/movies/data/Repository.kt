package ru.mirzacharlie.movies.data

import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getMovies(): Flow<List<MovieEntity>>

    fun getFavourites(): Flow<List<MovieEntity>>

    suspend fun getMovieById(id: Int): MovieEntity

    suspend fun saveMovies(movies: List<MovieEntity>)

    suspend fun updateFavourite(id: Int, isFavourite: Int)

    fun getLastLoadedPageNumber(): Int

    fun setLastLoadedPageNumber(lastPage: Int)
}