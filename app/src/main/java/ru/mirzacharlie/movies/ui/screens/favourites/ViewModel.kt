package ru.mirzacharlie.movies.ui.screens.favourites

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.map
import ru.mirzacharlie.movies.domain.usecases.GetFavouritesMoviesUseCase
import ru.mirzacharlie.movies.ui.base.BaseViewModel
import ru.mirzacharlie.movies.ui.models.toMovie

class FavouritesVM(getFavouritesMoviesUseCase: GetFavouritesMoviesUseCase) :
    BaseViewModel() {

    val movies = getFavouritesMoviesUseCase.execute()
        .map { it.map { e -> e.toMovie() } }.asLiveData()
}
