package ru.mirzacharlie.movies.data

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow
import ru.mirzacharlie.movies.models.MovieEntity

@Dao
interface MovieLocalDataSource {

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getPopulars(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE isFavourite == 1 ORDER BY popularity DESC")
    fun getFavourites(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id == :id")
    suspend fun getById(id: Int): MovieEntity

    @RawQuery(observedEntities = [MovieEntity::class])
    suspend fun rawQuery(query: SupportSQLiteQuery): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(data: List<MovieEntity>)

    @Update
    suspend fun update(data: MovieEntity)

    @Query("UPDATE movies SET isFavourite = :isFavourite WHERE id = :id")
    suspend fun updateFavourite(id: Int, isFavourite: Int): Int

    @Delete
    suspend fun delete(data: MovieEntity)
}