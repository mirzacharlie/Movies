package ru.mirzacharlie.movies.domain.models

import java.io.Serializable

data class MovieSearchParams(
    val title: String? = null,
    val rating: Float? = null,
    val isAdult: Boolean? = null
) : Serializable

sealed interface SearchParams : Serializable {

    fun query(): String

    class TitleSearchParamModel(
        val hint: String = "Title",
        var value: String? = null
    ) : SearchParams {
        override fun query(): String = value?.let { "title LIKE '%$it%'" } ?: ""
    }

    class RatingSearchParamModel(
        val hint: String = "Rating",
        var value: Float? = null
    ) : SearchParams {
        override fun query(): String = value?.let { "rating >= $value" } ?: ""
    }

    class AdultSearchParamModel(
        val hint: String = "Show adult content",
        var value: Boolean = false
    ) : SearchParams {
        override fun query(): String = if (value) "isAdult == 1" else "isAdult == 0"

    }
}
