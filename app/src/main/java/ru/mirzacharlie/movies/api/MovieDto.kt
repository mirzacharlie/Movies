package ru.mirzacharlie.movies.api

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Int,

    val title: String,

    @SerializedName("vote_average")
    val rating: Float,

    val popularity: Float,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String
)
