package ru.mirzacharlie.movies.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.mirzacharlie.movies.ui.screens.main.MainFragment
import ru.mirzacharlie.movies.ui.screens.main.MainModule

@Module
abstract class FragmentInjectorsModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainFragmentInjector(): MainFragment
}
