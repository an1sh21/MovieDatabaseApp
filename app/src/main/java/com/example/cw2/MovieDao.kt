package com.example.cw2

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE

@Dao
interface MovieDao {

   @Insert(onConflict = REPLACE)
   suspend fun insertAllMovies(movies: List<Movie>)

   @Query("SELECT * FROM movies")
   suspend fun getAllMovies(): List<Movie>

   @Query("SELECT * FROM movies WHERE actors LIKE  '%' || :actor || '%'")
   suspend fun getActors(actor: String): List<Movie>
}