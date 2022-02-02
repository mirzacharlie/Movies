package ru.mirzacharlie.movies.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.mirzacharlie.movies.ui.activities.MainActivity
import ru.mirzacharlie.movies.ui.activities.MainModule

@Module
abstract class ActivityInjectorsModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivityInjector(): MainActivity
}
