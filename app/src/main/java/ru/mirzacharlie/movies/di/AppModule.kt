package ru.mirzacharlie.movies.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.mirzacharlie.movies.App
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(app: App): Context =
        app.applicationContext

//    @Provides
//    @Singleton
//    fun provideRepository(
//        apiService: ApiService,
//        movieDao: MovieDao
//    ): Repository = Repository(apiService, movieDao)
}
