package ru.mirzacharlie.movies.ui.screens.movies

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
internal interface MoviesFragmentComponent : AndroidInjector<MoviesFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MoviesFragment>
}