package ru.mirzacharlie.movies.data

import androidx.lifecycle.LiveData

interface Repository {

    fun getMovies(): LiveData<List<MovieEntity>>

    fun getFavourites(): LiveData<List<MovieEntity>>

    suspend fun getMovieById(id: Int): MovieEntity

    suspend fun saveMovies(movies: List<MovieEntity>)

    suspend fun updateFavourite(id: Int, isFavourite: Int)

    fun getLastLoadedPageNumber(): Int

    fun setLastLoadedPageNumber(lastPage: Int)
}