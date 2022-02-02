package ru.mirzacharlie.movies.ui.screens.main

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import ru.mirzacharlie.movies.data.Repository
import ru.mirzacharlie.movies.di.InjectionViewModelProvider
import ru.mirzacharlie.movies.di.ViewModelInjection
import javax.inject.Inject

@Module
class MainModule {

    @Provides
    @ViewModelInjection
    fun provideClassesVM(
        fragment: MainFragment,
        viewModelProvider: InjectionViewModelProvider<MainVM>
    ): MainVM = viewModelProvider.get(fragment, MainVM::class)
}

class MainVM @Inject constructor(
    repository: Repository
) : ViewModel()
