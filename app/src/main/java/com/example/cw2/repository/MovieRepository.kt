package com.example.cw2.repository

import com.example.cw2.MovieResult
import com.example.cw2.model.Movie

interface MovieRepository{
    suspend fun getMovieByTitle(title: String): Movie
    suspend fun getMoviesBySearchTerm(title: String): List<MovieResult>
    suspend fun getMoviesByActor(actor: String): List<Movie>
    suspend fun saveMovie(movie: Movie)
    suspend fun addHardcodedMovies()
}