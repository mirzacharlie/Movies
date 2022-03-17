package ru.mirzacharlie.movies.ui.screens.favourites

import androidx.lifecycle.asLiveData
import ru.mirzacharlie.movies.data.Repository
import ru.mirzacharlie.movies.ui.base.BaseViewModel

class FavouritesVM(repository: Repository) : BaseViewModel() {

    val movies = repository.getMovies().asLiveData()
}
