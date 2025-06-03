package com.example.cw2.repository

import com.example.cw2.MovieResult
import com.example.cw2.data.parseMovieFromJson
import com.example.cw2.data.parseSearchResults
import com.example.cw2.hardcodedMovies
import com.example.cw2.model.Movie
import com.example.cw2.model.MovieDao
import com.example.cw2.network.MovieApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(private val dao: MovieDao) : MovieRepository {

    override suspend fun getMovieByTitle(title: String): Movie {
        return withContext(Dispatchers.IO) {
            val jsonString = MovieApiService.fetchMovieByTitle(title)
            parseMovieFromJson(jsonString)
        }
    }

    override suspend fun getMoviesBySearchTerm(title: String): List<MovieResult> {
        return withContext(Dispatchers.IO) {
            val jsonString = MovieApiService.fetchMoviesBySearchTerm(title)
            parseSearchResults(jsonString)
        }
    }

    override suspend fun getMoviesByActor(actor: String): List<Movie> {
        return withContext(Dispatchers.IO) {
            dao.getActors(actor)
        }
    }

    override suspend fun saveMovie(movie: Movie) {
        return withContext(Dispatchers.IO) {
            dao.insertAllMovies(listOf(movie))
        }
    }

    override suspend fun addHardcodedMovies() {
        return withContext(Dispatchers.IO) {
            dao.insertAllMovies(hardcodedMovies)
        }
    }
}