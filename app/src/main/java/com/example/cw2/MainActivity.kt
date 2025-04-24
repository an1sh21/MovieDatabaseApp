package com.example.cw2
import org.json.JSONObject
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cw2.ui.theme.Cw2Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import androidx.compose.foundation.lazy.items



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Cw2Theme {
                var currentScreen by remember { mutableStateOf("home") }
                if (currentScreen == "home") {
                    firstScreen(
                        onNavigate = { currentScreen = "searchMovies" },
                        onNavigateActors = { currentScreen = "searchActors" })
                } else if (currentScreen == "searchMovies") {
                    searchMoviesScreen(onBack = {currentScreen = "home"})
                } else if (currentScreen == "searchActors") {
                    searchActorsScreen(onBack = {currentScreen = "home"})
                }

            }
        }
    }


    @Composable
    fun firstScreen(onNavigate: () -> Unit, onNavigateActors: () -> Unit) {
        val context = LocalContext.current

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val db = AppDatabase.getDatabase(context)
                        db.movieDao().insertAllMovies(hardcodedMovies)
                        val allMovies = db.movieDao().getAllMovies()
                        allMovies.forEach { movie ->
                            Log.d("ROOM_CHECK", movie.title)
                        }

                    }
                }
            ) {
                Text("Add Movie to Database")
            }

            Spacer(modifier = Modifier.padding(16.dp))

            Button(onClick = {
                onNavigate()
                //Navigate to Search for Movies screen
            }) {
                Text("Search for Movies")
            }

            Spacer(modifier = Modifier.padding(16.dp))

            Button(onClick = {
                onNavigateActors()
                // Navigate to Search for Actors screen
            }) {
                Text("Search for Actors")
            }
        }
    }

    fun fetchMovieFromApi(title: String): String {
        val url = "https://www.omdbapi.com/?t=${title.replace(" ", "+")}&apikey=cc3e03a5"
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connect()

        val response = connection.inputStream.bufferedReader().readText()

        return response

    }


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

    @Composable
    fun searchMoviesScreen(onBack: () -> Unit) {
        val context = LocalContext.current


        var searchTitle by remember { mutableStateOf("") }
        var movie by remember { mutableStateOf<Movie?>(null) }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            OutlinedTextField(
                value = searchTitle,
                onValueChange = { searchTitle = it },
                label = { Text("Enter movie title") },
            )


            Spacer(modifier = Modifier.padding(24.dp))



            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val response = fetchMovieFromApi(searchTitle)
                    val parsed = parseMovieFromJson(response)


                    withContext(Dispatchers.Main) {
                        movie = parsed
                    }
                }
            }) {
                Text("Retrieve Movie")
            }


            Spacer(modifier = Modifier.padding(24.dp))


            movie?.let {
                Card(
                    modifier = Modifier
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text("🎬 Title: ${it.title}")
                        Text("📅 Year: ${it.year}")
                        Text("🔞 Rated: ${it.rated}")
                        Text("🎟️ Released: ${it.released}")
                        Text("⏱️ Runtime: ${it.runtime}")
                        Text("🎭 Genre: ${it.genre}")
                        Text("🎬 Director: ${it.director}")
                        Text("🧑‍🤝‍🧑 Actors: ${it.actors}")
                        Spacer(modifier = Modifier.padding(16.dp))
                        Text("📝 Plot: ${it.plot}")


                    }

                }
            }


            Spacer(modifier = Modifier.padding(24.dp))



            Button(onClick = {
                movie?.let {
                    CoroutineScope(Dispatchers.IO).launch {
                        val db = AppDatabase.getDatabase(context)
                        db.movieDao().insertAllMovies(listOf(it))


                    }


                }
            }) {
                Text("Save Movie to Database")
            }


            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {onBack()}
            ) {
                Text("⬅️ Back to Home")
            }
        }

    }


    @Composable
    fun searchActorsScreen(onBack: () -> Unit) {
        val context = LocalContext.current


        var searchActor by remember { mutableStateOf("") }
        var moviewithActor by remember { mutableStateOf<List<Movie>>(emptyList()) }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            OutlinedTextField(
                value = searchActor,
                onValueChange = { searchActor = it },
                label = { Text("Enter Actor name") },
            )


            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val db = AppDatabase.getDatabase(context)
                    val response = db.movieDao().getActors(searchActor)
                    withContext(Dispatchers.Main) {
                        moviewithActor = response

                    }
                }
            }) {
                Text("Retrieve Movie")
            }




            if (moviewithActor.isNotEmpty()) {
                LazyColumn {
                    items(moviewithActor) { movie ->
                        MovieActorCard(movie)


                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {onBack()}
            ) {
                Text("⬅️ Back to Home")
            }


        }
    }

    @Composable
    fun MovieActorCard(movie: Movie) {
        Card(
            modifier = Modifier,
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Text("🎬 Title: ${movie.title}")
            Text("📅 Year: ${movie.year}")
            Text("🔞 Rated: ${movie.rated}")
            Text("🎟️ Released: ${movie.released}")
            Text("⏱️ Runtime: ${movie.runtime}")
            Text("🎭 Genre: ${movie.genre}")
            Text("🎬 Director: ${movie.director}")
            Text("🧑‍🤝‍🧑 Actors: ${movie.actors}")
            Spacer(modifier = Modifier.padding(16.dp))
            Text("📝 Plot: ${movie.plot}")
        }



    }
}

























