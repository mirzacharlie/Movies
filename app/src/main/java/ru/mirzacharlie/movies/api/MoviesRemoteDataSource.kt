package ru.mirzacharlie.movies.api

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesRemoteDataSource {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/discover/"

        private const val PARAM_API_KEY = "api_key"
        private const val PARAM_LANGUAGE = "language"
        private const val PARAM_SORT_BY = "sort_by"
        private const val PARAM_PAGE = "page"

        private const val API_KEY = "79c59ba1bab058ffbcfd476a63254d09"
        private const val LANGUAGE_VALUE = "en-EN"
        private const val SORT_BY_POPULARITY = "popularity.desc"
        private const val SORT_BY_RATING = "vote_average.desc"
    }

    @GET("movie")
    suspend fun getPopular(
        @Query(PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(PARAM_LANGUAGE) language: String = LANGUAGE_VALUE,
        @Query(PARAM_SORT_BY) sortBy: String = SORT_BY_POPULARITY,
        @Query(PARAM_PAGE) page: Int
    ): ResultDto
}
