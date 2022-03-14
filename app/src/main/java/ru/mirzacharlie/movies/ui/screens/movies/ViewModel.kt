package ru.mirzacharlie.movies.ui.screens.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mirzacharlie.movies.api.ApiService
import ru.mirzacharlie.movies.data.Repository
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesVM
import ru.mirzacharlie.movies.utils.toEntity
import javax.inject.Inject
import javax.inject.Provider

class MoviesVM @Inject constructor(
    private val repository: Repository,
    private val apiService: ApiService
) : ViewModel() {

    val result = repository.movies

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
            if (repository.movies.value.isNullOrEmpty()) {
                loadNewPage()
            }
        }
    }

}
