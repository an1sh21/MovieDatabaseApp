package com.example.cw2.data

import com.example.cw2.MovieResult
import com.example.cw2.model.Movie
import org.json.JSONObject

fun parseMovieFromJson(jsonString: String): Movie {
    val obj = JSONObject(jsonString)

    return Movie(
        title = obj.getString("Title"),
        year = obj.getString("Year"),
        rated = obj.getString("Rated"),
        released = obj.getString("Released"),
        runtime = obj.getString("Runtime"),
        genre = obj.getString("Genre"),
        director = obj.getString("Director"),
        writer = obj.getString("Writer"),
        actors = obj.getString("Actors"),
        plot = obj.getString("Plot")
    )


}