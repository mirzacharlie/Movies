package ru.mirzacharlie.movies.ui.screens.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import ru.mirzacharlie.movies.api.ApiService
import ru.mirzacharlie.movies.data.Repository
import ru.mirzacharlie.movies.ui.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Provider

class FavouritesVM @Inject constructor(repository: Repository) : BaseViewModel() {

    val movies = repository.favourites

//    class Factory @Inject constructor(private val repository: Provider<Repository>) : ViewModelProvider.Factory {
//
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            require(modelClass == FavouritesVM::class.java)
//            return FavouritesVM(repository.get()) as T
//        }
//    }
}
