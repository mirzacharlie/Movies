package ru.mirzacharlie.movies.ui.models

interface SearchParams

data class TitleSearchParam(
    val name: String = "Title",
    var value: String? = null
) : SearchParams

data class RatingSearchParam(
    val name: String = "Rating",
    var value: Float? = null
) : SearchParams

data class IsAdultSearchParam(
    val name: String = "Show adult content",
    var value: Boolean? = null
) : SearchParams