package com.example.cw2.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cw2.MovieResult
import com.example.cw2.model.Movie

@Composable
fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "🎬 ${movie.title}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text("📅 Year: ${movie.year}", style = MaterialTheme.typography.bodyLarge)
            Text("🔞 Rated: ${movie.rated}", style = MaterialTheme.typography.bodyLarge)
            Text("🎟️ Released: ${movie.released}", style = MaterialTheme.typography.bodyLarge)
            Text("⏱️ Runtime: ${movie.runtime}", style = MaterialTheme.typography.bodyLarge)
            Text("🎭 Genre: ${movie.genre}", style = MaterialTheme.typography.bodyLarge)
            Text("🎬 Director: ${movie.director}", style = MaterialTheme.typography.bodyLarge)
            Text("🧑‍🤝‍🧑 Actors: ${movie.actors}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.padding(8.dp))
            Text("📝 ${movie.plot}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
    @Composable
    fun MovieCardSearch(movie: MovieResult) {
        Card(
            modifier = Modifier
                .padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(2.dp)) {
                Text(
                    text = "🎬 ${movie.title}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text("📅 Year: ${movie.year}", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }

