package ru.mirzacharlie.movies.ui.screens.movies

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.mirzacharlie.movies.domain.usecases.GetMoviesUseCase
import ru.mirzacharlie.movies.domain.usecases.HasSavedMoviesUseCase
import ru.mirzacharlie.movies.domain.usecases.LoadNewMoviesPageUseCase
import ru.mirzacharlie.movies.ui.base.BaseViewModel
import ru.mirzacharlie.movies.ui.models.toMovie

class MoviesVM(
    private val loadNewMoviesPageUseCase: LoadNewMoviesPageUseCase,
    private val hasSavedMoviesUseCase: HasSavedMoviesUseCase,
    getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel() {

    val result = getMoviesUseCase.execute()
        .map { it.map { e -> e.toMovie() } }.asLiveData()

    fun loadNewPage() {
        CoroutineScope(coroutineContext + Job()).launch {
            loadNewMoviesPageUseCase.execute()
        }
    }

    init {
        runBlocking(coroutineContext + Job()) {
            if (!hasSavedMoviesUseCase.execute()) {
                loadNewPage()
            }
        }
    }
}
