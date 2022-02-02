package ru.mirzacharlie.movies.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.mirzacharlie.movies.data.AppDatabase
import ru.mirzacharlie.movies.data.MovieDao
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase =
        AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideMovieDao(database: AppDatabase): MovieDao =
        database.movieDao()
}
