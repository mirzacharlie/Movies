package ru.mirzacharlie.movies.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.mirzacharlie.movies.ui.screens.movies.MoviesFragment
import ru.mirzacharlie.movies.ui.screens.movies.MoviesModule

@Module
abstract class FragmentInjectorsModule {

    @ContributesAndroidInjector(modules = [MoviesModule::class])
    abstract fun moviesFragmentInjector(): MoviesFragment
}
