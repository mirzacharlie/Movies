package ru.mirzacharlie.movies.ui.screens.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mirzacharlie.movies.api.ApiService
import ru.mirzacharlie.movies.data.Repository
import ru.mirzacharlie.movies.di.ViewModelKey
import ru.mirzacharlie.movies.ui.screens.moviedetails.MovieDetailsVM
import ru.mirzacharlie.movies.utils.toEntity
import javax.inject.Inject

@Module(subcomponents = [MoviesFragmentComponent::class])
internal abstract class MoviesModule {

//    @Binds @IntoMap @ClassKey(MoviesFragment::class)
//    internal abstract fun bindFragmentInjectorFactory(factory: MoviesFragmentComponent.Factory):
//        AndroidInjector.Factory<*>

    @Binds @IntoMap @ViewModelKey(MoviesVM::class)
    internal abstract fun bindViewModel(viewModel: MoviesVM): ViewModel

//    @Provides
//    @ViewModelInjection
//    fun provideMoviesVM(
//        fragment: MoviesFragment,
//        viewModelProvider: InjectionViewModelProvider<MoviesVM>
//    ): MoviesVM = viewModelProvider.get(fragment, MoviesVM::class)
}

class MoviesVM @Inject constructor(
    private val apiService: ApiService,
    private val repository: Repository
) : ViewModel() {

    val result = repository.movies

    fun loadNewPage() {
        viewModelScope.launch(Dispatchers.IO) {
            val page = repository.getLastLoadedPageNumber()
            Log.d("MoviesVM", "Page $page")
            repository.saveMovies(
                apiService.getPopular(page = page + 1).movies.map {
                    it.toEntity()
                }
            )
            repository.setLastLoadedPageNumber(page + 1)
        }
    }

    init {
        viewModelScope.launch {
            if (repository.movies.value.isNullOrEmpty()) {
                loadNewPage()
            }
        }
    }
}
