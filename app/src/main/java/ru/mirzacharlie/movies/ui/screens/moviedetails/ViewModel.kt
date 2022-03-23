package ru.mirzacharlie.movies.ui.screens.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.mirzacharlie.movies.domain.usecases.AddMovieToFavouritesUseCase
import ru.mirzacharlie.movies.domain.usecases.GetMovieByIdUseCase
import ru.mirzacharlie.movies.domain.usecases.RemoveMovieFromFavouritesUseCase
import ru.mirzacharlie.movies.ui.base.BaseViewModel
import ru.mirzacharlie.movies.ui.models.Movie
import ru.mirzacharlie.movies.ui.models.toMovie

class MovieDetailsVM(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val addMovieToFavouritesUseCase: AddMovieToFavouritesUseCase,
    private val removeMovieFromFavouritesUseCase: RemoveMovieFromFavouritesUseCase
) : BaseViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    fun requestMovie(id: Int) {
        CoroutineScope(coroutineContext + Job()).launch {
            _movie.postValue(getMovieByIdUseCase.execute(id).toMovie())
        }
    }

    fun updateFavourite() {
        CoroutineScope(coroutineContext + Job()).launch {
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
