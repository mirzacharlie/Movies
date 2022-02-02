package ru.mirzacharlie.movies.utils

import ru.mirzacharlie.movies.api.MovieDto
import ru.mirzacharlie.movies.data.MovieEntity

fun MovieDto.toEntity() =
    MovieEntity(
        id = this.id,
        title = this.title,
        rating = this.rating,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate
    )
