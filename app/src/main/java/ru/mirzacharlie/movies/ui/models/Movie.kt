package ru.mirzacharlie.movies.ui.models

import ru.mirzacharlie.movies.domain.models.MovieModel

data class Movie(
    val id: Int,
    val title: String,
    val rating: Float,
    val popularity: Float,
    val posterPath: String,
    val releaseDate: String?,
    val isFavourite: Boolean
)

fun MovieModel.toMovie() =
    Movie(
        id = this.id,
        title = this.title,
        rating = this.rating,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        isFavourite = this.isFavourite
    )