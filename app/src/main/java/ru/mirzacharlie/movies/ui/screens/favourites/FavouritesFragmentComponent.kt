package ru.mirzacharlie.movies.ui.screens.favourites

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import ru.mirzacharlie.movies.api.ApiService
import ru.mirzacharlie.movies.di.FragmentComponent
import ru.mirzacharlie.movies.di.ViewModelKey
import javax.inject.Scope

@Scope
annotation class FavouritesFragmentScope

@Module
interface FavouritesFragmentModule {

    @Binds @IntoMap @ViewModelKey(FavouritesVM::class)
    fun provideViewModel(viewModel: FavouritesVM): ViewModel

}

@Subcomponent(modules = [FavouritesFragmentModule::class])
@FavouritesFragmentScope
interface FavouritesFragmentComponent : FragmentComponent {

    fun inject(fragment: FavouritesFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): FavouritesFragmentComponent
    }
}
