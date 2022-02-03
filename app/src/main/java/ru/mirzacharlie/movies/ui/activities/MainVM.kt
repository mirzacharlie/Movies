package ru.mirzacharlie.movies.ui.activities

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import ru.mirzacharlie.movies.di.InjectionViewModelProvider
import ru.mirzacharlie.movies.di.ViewModelInjection
import javax.inject.Inject

@Module
class MainModule {
    @Provides
    @ViewModelInjection
    fun provideMainVM(
        activity: MainActivity,
        viewModelProvider: InjectionViewModelProvider<MainVM>
    ): MainVM = viewModelProvider.get(activity, MainVM::class)
}

class MainVM @Inject constructor() : ViewModel()
