package ru.mirzacharlie.movies.ui.screens.moviedetails

import androidx.lifecycle.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mirzacharlie.movies.data.MovieEntity
import ru.mirzacharlie.movies.data.Repository
import ru.mirzacharlie.movies.ui.base.BaseViewModel
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesVM
import javax.inject.Inject
import javax.inject.Provider

class MovieDetailsVM @Inject constructor(private val repository: Repository) : BaseViewModel() {

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

//    class Factory @Inject constructor(private val repository: Provider<Repository>) : ViewModelProvider.Factory {
//
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            require(modelClass == MovieDetailsVM::class.java)
//            return MovieDetailsVM(repository.get()) as T
//        }
//    }
}
