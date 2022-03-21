package ru.mirzacharlie.movies.ui.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.mirzacharlie.movies.domain.usecases.GetMoviesUseCase
import ru.mirzacharlie.movies.domain.usecases.HasSavedMoviesUseCase
import ru.mirzacharlie.movies.domain.usecases.LoadNewMoviesPageUseCase
import ru.mirzacharlie.movies.ui.models.toMovie

class MoviesVM(
    private val loadNewMoviesPageUseCase: LoadNewMoviesPageUseCase,
    private val hasSavedMoviesUseCase: HasSavedMoviesUseCase,
    getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    val result = getMoviesUseCase.execute()
        .map { it.map { e -> e.toMovie() } }.asLiveData()

    fun loadNewPage() {
        viewModelScope.launch {
            loadNewMoviesPageUseCase.execute()
        }
    }

    init {
        runBlocking(Dispatchers.Default) {
            if (!hasSavedMoviesUseCase.execute()) {
                loadNewPage()
            }
        }
    }
}
