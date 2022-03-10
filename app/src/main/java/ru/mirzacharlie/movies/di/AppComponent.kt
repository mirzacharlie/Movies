package ru.mirzacharlie.movies.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import ru.mirzacharlie.movies.App
import ru.mirzacharlie.movies.ui.activities.MainActivityModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        MainActivityModule::class,
        ActivityInjectorsModule::class,
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)
}
