package ru.mirzacharlie.movies.ui.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.mirzacharlie.movies.domain.usecases.GetMoviesUseCase
import ru.mirzacharlie.movies.domain.usecases.HasSavedMoviesUseCase
import ru.mirzacharlie.movies.domain.usecases.LoadNewMoviesPageUseCase

class MoviesVM(
    private val loadNewMoviesPageUseCase: LoadNewMoviesPageUseCase,
    private val hasSavedMoviesUseCase: HasSavedMoviesUseCase,
    getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    val result = getMoviesUseCase.execute().asLiveData()

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
