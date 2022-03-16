package ru.mirzacharlie.movies.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import ru.mirzacharlie.movies.App
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideApplicationContext(app: App): Context =
        app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences("AppPreferences", AppCompatActivity.MODE_PRIVATE)
}
