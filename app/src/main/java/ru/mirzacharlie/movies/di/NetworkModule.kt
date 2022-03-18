package ru.mirzacharlie.movies.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mirzacharlie.movies.api.MoviesRemoteDataSource
import java.util.concurrent.TimeUnit

val networkModule = module {

    single<OkHttpClient> {
        OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(MoviesRemoteDataSource.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<MoviesRemoteDataSource> {
        val retrofit: Retrofit = get()
        retrofit.create(MoviesRemoteDataSource::class.java)
    }
}