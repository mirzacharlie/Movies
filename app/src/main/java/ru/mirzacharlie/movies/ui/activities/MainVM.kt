package ru.mirzacharlie.movies.ui.activities

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ru.mirzacharlie.movies.ui.base.BaseViewModel
import javax.inject.Inject

class MainVM @Inject constructor() : BaseViewModel()
