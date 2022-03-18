package ru.mirzacharlie.movies.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import org.koin.dsl.module
import ru.mirzacharlie.movies.RepositoryImpl
import ru.mirzacharlie.movies.data.AppDatabase
import ru.mirzacharlie.movies.data.MoviesLocalDataSource
import ru.mirzacharlie.movies.data.PreferencesManager
import ru.mirzacharlie.movies.domain.repository.Repository

val dataModule = module {

    single<AppDatabase> {
        AppDatabase.getInstance(context = get())
    }

    single<MoviesLocalDataSource> {
        val database: AppDatabase = get()
        database.movieDao()
    }

    single<PreferencesManager> {
        val context: Context = get()
        PreferencesManager(
            context.getSharedPreferences("AppPreferences", AppCompatActivity.MODE_PRIVATE)
        )
    }

    single<Repository> {
        RepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get(),
            preferencesManager = get()
        )
    }
}
