package ru.mirzacharlie.movies.ui.screens.favourites

import dagger.Module
import dagger.Provides
import ru.mirzacharlie.movies.data.Repository
import ru.mirzacharlie.movies.di.InjectionViewModelProvider
import ru.mirzacharlie.movies.di.ViewModelInjection
import ru.mirzacharlie.movies.ui.base.BaseViewModel
import javax.inject.Inject

@Module
class FavouritesModule {

    @Provides
    @ViewModelInjection
    fun provideFavouritesVM(
        fragment: FavouritesFragment,
        viewModelProvider: InjectionViewModelProvider<FavouritesVM>
    ): FavouritesVM = viewModelProvider.get(fragment, FavouritesVM::class)
}

class FavouritesVM @Inject constructor(repository: Repository) : BaseViewModel() {

    val movies = repository.favourites
}
