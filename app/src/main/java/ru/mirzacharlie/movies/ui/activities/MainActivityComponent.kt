package ru.mirzacharlie.movies.ui.activities

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import ru.mirzacharlie.movies.di.ViewModelKey
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesVM
import javax.inject.Scope

@Scope
annotation class MainActivityScope

@Module
interface MainActivityModule {

    @Binds @IntoMap @ViewModelKey(MainVM::class)
    fun provideViewModel(viewModel: MainVM): ViewModel
}

@Subcomponent(modules = [MainActivityModule::class])
@MainActivityScope
interface MainActivityComponent {

    fun inject(activity: MainActivity)

    @Subcomponent.Builder
    interface Builder {

        fun build(): MainActivityComponent
    }
}