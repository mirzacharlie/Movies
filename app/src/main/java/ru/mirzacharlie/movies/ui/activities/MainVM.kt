package ru.mirzacharlie.movies.ui.activities

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ru.mirzacharlie.movies.ui.base.BaseViewModel
import javax.inject.Inject

@Module(subcomponents = [MainActivityComponent::class])
abstract class MainActivityModule {

    @Binds @IntoMap @ClassKey(MainActivity::class)
    internal abstract fun bindMainActivityFactory(factory: MainActivityComponent.Factory): AndroidInjector.Factory<*>
}

class MainVM @Inject constructor() : BaseViewModel()
