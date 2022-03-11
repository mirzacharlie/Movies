package ru.mirzacharlie.movies.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.mirzacharlie.movies.ui.activities.MainActivity
import ru.mirzacharlie.movies.ui.activities.MainActivityModule

@Module
abstract class ActivityInjectorsModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivityInjector(): MainActivity
}
