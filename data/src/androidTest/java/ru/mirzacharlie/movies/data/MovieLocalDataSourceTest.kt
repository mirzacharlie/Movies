package ru.mirzacharlie.movies.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.mirzacharlie.movies.models.MovieEntity
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class MovieLocalDataSourceTest {

    private lateinit var db: AppDatabase
    private lateinit var moviesLocalDataSource: MovieLocalDataSource

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries()
            .build()
        moviesLocalDataSource = db.movieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeMovieAndReadById() {
        runTest {
            val movie = MovieEntity(
                id = 123,
                title = "Spider Man",
                rating = 1.2f,
                popularity = 2.34f,
                posterPath = "url",
                releaseDate = "22.02.2222",
                isFavourite = 0
            )

            moviesLocalDataSource.insert(movie)

            val byId = moviesLocalDataSource.getById(123)

            assertThat(byId).isEqualTo(movie)
        }
    }

    @Test
    @Throws(Exception::class)
    fun writeMovieListAndRead() {
        runTest {
            val movies = listOf(
                MovieEntity(
                    id = 1,
                    title = "Spider Man",
                    rating = 5.6f,
                    popularity = 4.34f,
                    posterPath = "url",
                    releaseDate = "22.02.2222",
                    isFavourite = 0
                ),
                MovieEntity(
                    id = 2,
                    title = "Spider",
                    rating = 1.23f,
                    popularity = 1.314f,
                    posterPath = "url2",
                    releaseDate = "22.03.2222",
                    isFavourite = 1
                ),
                MovieEntity(
                    id = 3,
                    title = "Spider Pig",
                    rating = 2.75f,
                    popularity = 2.5f,
                    posterPath = "url3",
                    releaseDate = "22.03.2220",
                    isFavourite = 1
                )
            )

            moviesLocalDataSource.insertList(movies)

            val fromDb = moviesLocalDataSource.getPopulars().first()

            assertThat(fromDb).isEqualTo(movies.sortedByDescending { it.popularity })
        }
    }

    @Test
    fun addMovieToFavouritesAndRead() {
        runTest {
            val movie = MovieEntity(
                id = 123,
                title = "Spider Man",
                rating = 1.2f,
                popularity = 2.34f,
                posterPath = "url",
                releaseDate = "22.02.2222",
                isFavourite = 0
            )

            val favMovie = MovieEntity(
                id = 123,
                title = "Spider Man",
                rating = 1.2f,
                popularity = 2.34f,
                posterPath = "url",
                releaseDate = "22.02.2222",
                isFavourite = 1
            )

            moviesLocalDataSource.insert(movie)
            moviesLocalDataSource.updateFavourite(movie.id, 1)

            val fromDb = moviesLocalDataSource.getFavourites().first()

            assertThat(fromDb).contains(favMovie)
        }
    }

}