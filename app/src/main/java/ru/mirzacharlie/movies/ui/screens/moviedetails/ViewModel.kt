package ru.mirzacharlie.movies.ui.screens.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mirzacharlie.movies.data.MovieEntity
import ru.mirzacharlie.movies.data.Repository

class MovieDetailsVM(private val repository: Repository) : ViewModel() {

    private val _movie = MutableLiveData<MovieEntity>()
    val movie: LiveData<MovieEntity> get() = _movie

    fun requestMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _movie.postValue(repository.getMovieById(id))
        }
    }

    fun updateFavourite() {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = _movie.value ?: return@launch
            if (movie.isFavourite == 0) {
                repository.updateFavourite(movie.id, 1)
            } else {
                repository.updateFavourite(movie.id, 0)
            }
            requestMovie(movie.id)
        }
    }
}
