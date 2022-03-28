package ru.mirzacharlie.movies.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.mirzacharlie.movies.domain.models.MovieModel

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val rating: Float,
    val popularity: Float,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    val isAdult: Int = 0,
    val isFavourite: Int = 0
)

fun MovieEntity.toModel() =
    MovieModel(
        id = this.id,
        title = this.title,
        rating = this.rating,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        isAdult = this.isAdult == 1,
        isFavourite = this.isFavourite == 1
    )

fun MovieModel.toEntity() =
    MovieEntity(
        id = this.id,
        title = this.title,
        rating = this.rating,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        isAdult = if (isFavourite) 1 else 0,
        isFavourite = if (isFavourite) 1 else 0
    )
