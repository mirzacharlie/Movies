package ru.mirzacharlie.movies.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY popularity")
    fun getPopulars(): LiveData<MovieEntity>

    @Query("SELECT * FROM movies ORDER BY rating")
    fun getTopRated(): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(data: List<MovieEntity>)

    @Update
    suspend fun update(data: MovieEntity)

    @Delete
    suspend fun delete(data: MovieEntity)
}
