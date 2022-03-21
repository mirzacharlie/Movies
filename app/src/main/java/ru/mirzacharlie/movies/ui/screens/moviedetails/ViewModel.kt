package ru.mirzacharlie.movies.ui.screens.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mirzacharlie.movies.domain.usecases.AddMovieToFavouritesUseCase
import ru.mirzacharlie.movies.domain.usecases.GetMovieByIdUseCase
import ru.mirzacharlie.movies.domain.usecases.RemoveMovieFromFavouritesUseCase
import ru.mirzacharlie.movies.ui.models.Movie
import ru.mirzacharlie.movies.ui.models.toMovie

class MovieDetailsVM(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val addMovieToFavouritesUseCase: AddMovieToFavouritesUseCase,
    private val removeMovieFromFavouritesUseCase: RemoveMovieFromFavouritesUseCase
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    fun requestMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _movie.postValue(getMovieByIdUseCase.execute(id).toMovie())
        }
    }

    fun updateFavourite() {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = _movie.value ?: return@launch
            if (movie.isFavourite) {
                removeMovieFromFavouritesUseCase.execute(movie.id)
            } else {
                addMovieToFavouritesUseCase.execute(movie.id)
            }
            requestMovie(movie.id)
        }
    }
}
