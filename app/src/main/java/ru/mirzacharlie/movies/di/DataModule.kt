package ru.mirzacharlie.movies.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import org.koin.dsl.module
import ru.mirzacharlie.movies.PreferencesManager
import ru.mirzacharlie.movies.data.AppDatabase
import ru.mirzacharlie.movies.data.MovieDao
import ru.mirzacharlie.movies.data.RepositoryImpl

val dataModule = module {

    single<AppDatabase> {
        AppDatabase.getInstance(context = get())
    }

    single<MovieDao> {
        val database: AppDatabase = get()
        database.movieDao()
    }

    single<PreferencesManager> {
        val context: Context = get()
        PreferencesManager(
            context.getSharedPreferences("AppPreferences", AppCompatActivity.MODE_PRIVATE)
        )
    }

    single<RepositoryImpl> {
        RepositoryImpl(movieDao = get(), preferencesManager = get())
    }
}
