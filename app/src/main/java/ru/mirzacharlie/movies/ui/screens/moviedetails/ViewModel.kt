package ru.mirzacharlie.movies.ui.screens.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.launch
import ru.mirzacharlie.movies.data.MovieEntity
import ru.mirzacharlie.movies.data.Repository
import ru.mirzacharlie.movies.di.InjectionViewModelProvider
import ru.mirzacharlie.movies.di.ViewModelInjection
import javax.inject.Inject

@Module
class MovieDetailsModule {

    @Provides
    @ViewModelInjection
    fun provideMovieDetailsVM(
        fragment: MovieDetailsFragment,
        viewModelProvider: InjectionViewModelProvider<MovieDetailsVM>
    ): MovieDetailsVM = viewModelProvider.get(fragment, MovieDetailsVM::class)
}

class MovieDetailsVM @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _movie = MutableLiveData<MovieEntity>()
    val movie: LiveData<MovieEntity> get() = _movie

    fun requestMovie(id: Int) {
        viewModelScope.launch {
            val m = repository.getMovieById(id)
            _movie.value = m
        }
    }
}