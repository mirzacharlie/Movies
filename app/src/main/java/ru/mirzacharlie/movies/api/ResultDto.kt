package ru.mirzacharlie.movies.api

import com.google.gson.annotations.SerializedName

data class ResultDto(
    val page: Int,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int,
    @SerializedName("results")
    val movies: List<MovieDto>
)
