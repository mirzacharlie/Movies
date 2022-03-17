package ru.mirzacharlie.movies.ui.screens.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mirzacharlie.movies.api.ApiService
import ru.mirzacharlie.movies.data.RepositoryImpl
import ru.mirzacharlie.movies.utils.toEntity

class MoviesVM(
    private val apiService: ApiService,
    private val repository: RepositoryImpl
) : ViewModel() {

    val result = repository.getMovies()

    fun loadNewPage() {
        viewModelScope.launch(Dispatchers.IO) {
            val page = repository.getLastLoadedPageNumber()
            Log.d("MoviesVM", "Page $page")
            repository.saveMovies(
                apiService.getPopular(page = page + 1).movies.map {
                    it.toEntity()
                }
            )
            repository.setLastLoadedPageNumber(page + 1)
        }
    }

    init {
        viewModelScope.launch {
            if (repository.getMovies().value.isNullOrEmpty()) {
                loadNewPage()
            }
        }
    }
}
