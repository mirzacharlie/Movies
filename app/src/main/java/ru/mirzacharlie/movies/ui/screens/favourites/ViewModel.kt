package ru.mirzacharlie.movies.ui.screens.favourites

import androidx.lifecycle.asLiveData
import ru.mirzacharlie.movies.domain.usecases.GetFavouritesMoviesUseCase
import ru.mirzacharlie.movies.ui.base.BaseViewModel

class FavouritesVM(getFavouritesMoviesUseCase: GetFavouritesMoviesUseCase) :
    BaseViewModel() {

    val movies = getFavouritesMoviesUseCase.execute().asLiveData()
}
