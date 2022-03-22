package ru.mirzacharlie.movies.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import org.koin.dsl.module
import ru.mirzacharlie.movies.data.AppDatabase
import ru.mirzacharlie.movies.data.MovieLocalDataSource
import ru.mirzacharlie.movies.data.PreferencesManager
import ru.mirzacharlie.movies.domain.repository.MovieRepository
import ru.mirzacharlie.movies.domain.repository.PageRepository
import ru.mirzacharlie.movies.repositories.MovieRepositoryImpl
import ru.mirzacharlie.movies.repositories.PageRepositoryImpl

val dataModule = module {

    single<AppDatabase> {
        AppDatabase.getInstance(context = get())
    }

    single<MovieLocalDataSource> {
        val database: AppDatabase = get()
        database.movieDao()
    }

    single<PreferencesManager> {
        val context: Context = get()
        PreferencesManager(
            context.getSharedPreferences("AppPreferences", AppCompatActivity.MODE_PRIVATE)
        )
    }

    single<MovieRepository> {
        MovieRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    single<PageRepository> {
        PageRepositoryImpl(preferencesManager = get())
    }
}
