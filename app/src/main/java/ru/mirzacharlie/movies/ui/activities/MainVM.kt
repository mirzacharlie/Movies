package ru.mirzacharlie.movies.ui.activities

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
class MainModule {
    @Provides
    @ViewModelInjection
    fun provideMainVM(
        activity: MainActivity,
        viewModelProvider: InjectionViewModelProvider<MainVM>
    ): MainVM = viewModelProvider.get(activity, MainVM::class)
}

class MainVM @Inject constructor(private val repository: Repository) : ViewModel() {

    val result = repository.movies

    fun request() {
        viewModelScope.launch {
            repository.requestPopular()
        }
    }
}
