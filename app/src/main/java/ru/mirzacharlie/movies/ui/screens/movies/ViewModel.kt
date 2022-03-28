package ru.mirzacharlie.movies.ui.screens.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.mirzacharlie.movies.domain.models.MovieSearchParams
import ru.mirzacharlie.movies.domain.usecases.GetMoviesUseCase
import ru.mirzacharlie.movies.domain.usecases.HasSavedMoviesUseCase
import ru.mirzacharlie.movies.domain.usecases.LoadNewMoviesPageUseCase
import ru.mirzacharlie.movies.domain.usecases.SearchMovieUseCase
import ru.mirzacharlie.movies.ui.base.BaseViewModel
import ru.mirzacharlie.movies.ui.models.Movie
import ru.mirzacharlie.movies.ui.models.toMovie

class MoviesVM(
    private val loadNewMoviesPageUseCase: LoadNewMoviesPageUseCase,
    private val hasSavedMoviesUseCase: HasSavedMoviesUseCase,
    getMoviesUseCase: GetMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) : BaseViewModel() {

    private var _state = MutableLiveData<FragmentState>()
    val state: MutableLiveData<FragmentState> get() = _state

    private val movies = getMoviesUseCase.execute()
        .map { it.map { e -> e.toMovie() } }.asLiveData()

    fun loadNewPage() {
        CoroutineScope(coroutineContext + Job()).launch {
            loadNewMoviesPageUseCase.execute()
        }
    }

    fun search(params: MovieSearchParams) {
        CoroutineScope(coroutineContext + Job()).launch {
            val searchResult = searchMovieUseCase.execute(params).map { it.toMovie() }

            _state.postValue(FragmentState.SearchResult(searchResult))
        }
    }

    init {
        runBlocking(coroutineContext + Job()) {
            if (!hasSavedMoviesUseCase.execute()) {
                loadNewPage()
            }
        }
        _state.value = FragmentState.Popular(movies)
    }
}

sealed interface FragmentState {
    data class Popular(val movies: LiveData<List<Movie>>) : FragmentState
    data class SearchResult(val movies: List<Movie>) : FragmentState
}
