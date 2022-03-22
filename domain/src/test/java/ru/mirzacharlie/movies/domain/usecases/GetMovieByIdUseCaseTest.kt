package ru.mirzacharlie.movies.domain.usecases

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import ru.mirzacharlie.movies.domain.models.MovieModel
import ru.mirzacharlie.movies.domain.repository.MovieRepository

class GetMovieByIdUseCaseTest {

    private val repository = mock<MovieRepository>()

    @Test
    suspend fun `should return the same data as in repository`() {

        val testMovie = MovieModel(
            id = 1,
            title = "Spider Man",
            rating = 1.2345f,
            popularity = 2.3456f,
            posterPath = "posterUrl",
            releaseDate = "22.02.2022",
            isFavourite = true
        )

        Mockito.`when`(repository.getMovieById(1)).thenReturn(testMovie)

        val useCase = GetMovieByIdUseCase(repository)
        val actual = useCase.execute(1)
        val expected = MovieModel(
            id = 1,
            title = "Spider Man",
            rating = 1.2345f,
            popularity = 2.3456f,
            posterPath = "posterUrl",
            releaseDate = "22.02.2022",
            isFavourite = true
        )

        Assertions.assertEquals(expected, actual)
    }
}