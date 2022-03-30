package ru.mirzacharlie.movies.ui.screens.favourites

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.mirzacharlie.movies.data.Repository
import ru.mirzacharlie.movies.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesVM @Inject constructor(repository: Repository) : BaseViewModel() {

    val movies = repository.favourites
}
