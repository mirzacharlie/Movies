package ru.mirzacharlie.movies.ui.screens.moviedetails

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
internal interface MovieDetailsFragmentComponent : AndroidInjector<MovieDetailsFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MovieDetailsFragment>
}
