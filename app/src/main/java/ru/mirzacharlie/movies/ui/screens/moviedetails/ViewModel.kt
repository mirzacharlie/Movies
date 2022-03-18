package ru.mirzacharlie.movies.ui.screens.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mirzacharlie.movies.data.MovieEntity
import ru.mirzacharlie.movies.domain.usecases.AddMovieToFavouritesUseCase
import ru.mirzacharlie.movies.domain.usecases.GetMovieByIdUseCase
import ru.mirzacharlie.movies.domain.usecases.RemoveMovieFromFavouritesUseCase

class MovieDetailsVM(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val addMovieToFavouritesUseCase: AddMovieToFavouritesUseCase,
    private val removeMovieFromFavouritesUseCase: RemoveMovieFromFavouritesUseCase
) : ViewModel() {

    private val _movie = MutableLiveData<MovieEntity>()
    val movie: LiveData<MovieEntity> get() = _movie

    fun requestMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _movie.postValue(getMovieByIdUseCase.execute(id))
        }
    }

    fun updateFavourite() {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = _movie.value ?: return@launch
            if (movie.isFavourite == 0) {
                addMovieToFavouritesUseCase.execute(movie.id)
            } else {
                removeMovieFromFavouritesUseCase.execute(movie.id)
            }
            requestMovie(movie.id)
        }
    }
}
