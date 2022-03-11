package ru.mirzacharlie.movies.di

import androidx.lifecycle.ViewModelProvider
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}