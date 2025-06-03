package com.example.cw2.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

   @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
   suspend fun insertAllMovies(movies: List<Movie>)

   @Query("SELECT * FROM movies")
   suspend fun getAllMovies(): List<Movie>

   @Query("SELECT * FROM movies WHERE LOWER(actors) LIKE '%' || LOWER(:query) || '%'")
   fun getActors(query: String): List<Movie>
}
