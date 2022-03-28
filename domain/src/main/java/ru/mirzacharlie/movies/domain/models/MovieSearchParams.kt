package ru.mirzacharlie.movies.domain.models

import java.io.Serializable

data class MovieSearchParams(
    val title: String? = null,
    val rating: Float? = null,
    val isAdult: Boolean? = null
) : Serializable
