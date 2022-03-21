package ru.mirzacharlie.movies.domain.models

data class MovieModel(
    val id: Int,
    val title: String,
    val rating: Float,
    val popularity: Float,
    val posterPath: String,
    val releaseDate: String?,
    val isFavourite: Boolean
)
