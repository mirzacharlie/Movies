package ru.mirzacharlie.movies.utils

import ru.mirzacharlie.movies.domain.models.MovieDto
import ru.mirzacharlie.movies.domain.models.MovieEntity

fun MovieDto.toEntity() =
    MovieEntity(
        id = this.id,
        title = this.title,
        rating = this.rating,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate
    )
