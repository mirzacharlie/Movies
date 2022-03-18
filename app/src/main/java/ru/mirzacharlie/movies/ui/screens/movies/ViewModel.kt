package ru.mirzacharlie.movies.ui.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MoviesVM(
    private val loadNewMoviesPageUseCase: ru.mirzacharlie.movies.domain.usecases.LoadNewMoviesPageUseCase,
    private val hasSavedMoviesUseCase: ru.mirzacharlie.movies.domain.usecases.HasSavedMoviesUseCase,
    getMoviesUseCase: ru.mirzacharlie.movies.domain.usecases.GetMoviesUseCase
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
