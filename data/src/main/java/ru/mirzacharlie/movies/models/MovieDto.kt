package ru.mirzacharlie.movies.models

import com.google.gson.annotations.SerializedName
import ru.mirzacharlie.movies.domain.models.MovieModel

data class MovieDto(
    val id: Int,

    val title: String,

    @SerializedName("vote_average")
    val rating: Float,

    val popularity: Float,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String?
)

fun MovieDto.toEntity() =
    MovieEntity(
        id = this.id,
        title = this.title,
        rating = this.rating,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate
    )

fun MovieDto.toModel() =
    MovieModel(
        id = this.id,
        title = this.title,
        rating = this.rating,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        isFavourite = false
    )
