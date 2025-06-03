package com.example.cw2.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cw2.model.AppDatabase
import com.example.cw2.repository.MovieRepositoryImpl
import com.example.cw2.viewmodel.MovieViewModel
import com.example.cw2.viewmodel.MovieViewModelFactory

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val movieDao = AppDatabase.getDatabase(context).movieDao()
    val repository = remember { MovieRepositoryImpl(movieDao) }
    val factory = remember { MovieViewModelFactory(repository) }
    val movieViewModel: MovieViewModel = viewModel(factory = factory)
    val currentScreen by movieViewModel.currentScreen
    when (currentScreen) {
        "home" -> HomeScreen(
            onNavigate = { movieViewModel.navigate("search") },
            onNavigateActors = { movieViewModel.navigate("actor") },
            onNavigateMoviesCase = { movieViewModel.navigate("allMovies") }
        )
        "search" -> searchMoviesScreen(onBack = { movieViewModel.navigate("home") })
        "actor" -> searchByActorScreen(onBack = { movieViewModel.navigate("home") })
        "allMovies" -> searchAllMoviesScreen(onBack = { movieViewModel.navigate("home") })
    }
}