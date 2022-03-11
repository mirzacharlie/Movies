package ru.mirzacharlie.movies.ui.screens.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ru.mirzacharlie.movies.data.Repository
import ru.mirzacharlie.movies.di.ViewModelKey
import ru.mirzacharlie.movies.ui.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Provider

@Module(subcomponents = [FavoritesFragmentComponent::class])
internal abstract class FavouritesModule {

//    @Binds @IntoMap @ClassKey(FavouritesFragment::class)
//    internal abstract fun bindFragmentInjectorFactory(factory: FavoritesFragmentComponent.Factory):
//        AndroidInjector.Factory<*>

    @Binds @IntoMap @ViewModelKey(FavouritesVM::class)
    internal abstract fun bindViewModel(viewModel: FavouritesVM): ViewModel
}

internal class FavouritesVM @Inject constructor(repository: Repository) : BaseViewModel() {

    val movies = repository.favourites

//    class Factory @Inject constructor(
//        private val repository: Provider<Repository>
//    ) : ViewModelProvider.Factory {
//
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            require(modelClass == FavouritesVM::class.java)
//            return FavouritesVM(repository.get()) as T
//        }
//    }
}
