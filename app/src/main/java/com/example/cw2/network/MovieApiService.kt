package com.example.cw2.network

import java.net.HttpURLConnection
import java.net.URL

object MovieApiService {

    fun fetchMovieByTitle(title: String): String {
        val url = "https://www.omdbapi.com/?t=${title.replace(" ", "+")}&apikey=cc3e03a5"
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connect()
        return connection.inputStream.bufferedReader().readText()
    }

    fun fetchMoviesBySearchTerm(title: String): String {
        val url = "https://www.omdbapi.com/?s=${title.replace(" ", "+")}&apikey=cc3e03a5"
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connect()
        return connection.inputStream.bufferedReader().readText()
    }
}
