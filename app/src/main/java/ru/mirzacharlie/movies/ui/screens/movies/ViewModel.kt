package ru.mirzacharlie.movies.ui.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.launch
import ru.mirzacharlie.movies.data.Repository
import ru.mirzacharlie.movies.di.InjectionViewModelProvider
import ru.mirzacharlie.movies.di.ViewModelInjection
import javax.inject.Inject

@Module
class MoviesModule {

    @Provides
    @ViewModelInjection
    fun provideMoviesVM(
        fragment: MoviesFragment,
        viewModelProvider: InjectionViewModelProvider<MoviesVM>
    ): MoviesVM = viewModelProvider.get(fragment, MoviesVM::class)
}

class MoviesVM @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val result = repository.movies

    fun request() {
        viewModelScope.launch {
            repository.requestPopular()
        }
    }
}
