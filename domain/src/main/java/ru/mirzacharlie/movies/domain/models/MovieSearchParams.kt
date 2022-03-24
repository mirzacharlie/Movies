package ru.mirzacharlie.movies.domain.models

data class MovieSearchParams(
    val title: String,
    val rating: Float,
    val isAdult: Boolean
)
