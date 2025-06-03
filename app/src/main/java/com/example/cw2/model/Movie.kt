package com.example.cw2.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: String = UUID.randomUUID().toString(), // Unique ID generated
    val title: String,
    val year: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String
)
