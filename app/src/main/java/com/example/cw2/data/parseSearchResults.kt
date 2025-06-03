package com.example.cw2.data

import com.example.cw2.MovieResult
import com.example.cw2.model.Movie
import org.json.JSONObject

fun parseSearchResults(json: String): List<MovieResult> {
    val arr = JSONObject(json).getJSONArray("Search")
    return (0 until arr.length()).map { i ->
        val obj = arr.getJSONObject(i)
        MovieResult(
            title = obj.getString("Title"),
            year = obj.getString("Year"),
        )
    }
}