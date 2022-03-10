package ru.mirzacharlie.movies.ui.screens.favourites

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
internal interface FavoritesFragmentComponent : AndroidInjector<FavouritesFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<FavouritesFragment>
}
