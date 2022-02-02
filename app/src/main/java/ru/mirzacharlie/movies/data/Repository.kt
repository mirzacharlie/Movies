package ru.mirzacharlie.movies.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.mirzacharlie.movies.api.ApiService
import ru.mirzacharlie.movies.utils.toEntity
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) {

    private val _movies = MutableLiveData<List<MovieEntity>>()
    val movies: LiveData<List<MovieEntity>> get() = _movies

    suspend fun requestPopular() {
        val result = apiService.getPopular(page = 1).movies
        _movies.value = result.map { it.toEntity() }
    }
}
