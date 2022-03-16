package ru.mirzacharlie.movies.ui.screens.favourites

import ru.mirzacharlie.movies.data.Repository
import ru.mirzacharlie.movies.ui.base.BaseViewModel
import javax.inject.Inject

class FavouritesVM @Inject constructor(repository: Repository) : BaseViewModel() {

    val movies = repository.favourites
}
