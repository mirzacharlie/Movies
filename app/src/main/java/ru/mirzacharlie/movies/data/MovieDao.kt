package ru.mirzacharlie.movies.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getPopulars(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE isFavourite == 1 ORDER BY popularity DESC")
    fun getFavourites(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id == :id")
    suspend fun getById(id: Int): MovieEntity

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
