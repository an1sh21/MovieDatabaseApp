package com.example.cw2.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cw2.model.AppDatabase
import com.example.cw2.viewmodel.MovieViewModel
import com.example.cw2.repository.MovieRepositoryImpl
import com.example.cw2.viewmodel.MovieViewModelFactory

@Composable
fun searchMoviesScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val movieDao = AppDatabase.getDatabase(context).movieDao()
    val repository = MovieRepositoryImpl(movieDao)
    val movieViewModel: MovieViewModel =
        viewModel(factory = MovieViewModelFactory(repository))
    val movie by movieViewModel.movie
    val isLoading by movieViewModel.isLoading
    var searchTitle by rememberSaveable { mutableStateOf("") }

    val dialogState by movieViewModel.dialogState



    // Add these dialogs
    if (dialogState.show && dialogState.message != null) {
        if (dialogState.isError){
            ErrorDialog(message = dialogState.message!!) {
                movieViewModel.resetDialogState()
            }
        } else {
            SuccessDialog(message = dialogState.message!!) {
                movieViewModel.resetDialogState()
            }
        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        OutlinedTextField(
            value = searchTitle,
            onValueChange = { searchTitle = it },
            label = { Text("Enter movie title") },
        )


        Spacer(modifier = Modifier.padding(24.dp))



        Button(onClick = {
            movieViewModel.fetchMovieByTitle(searchTitle)
        }) {
            Text("Retrieve Movie")
        }


        Spacer(modifier = Modifier.padding(24.dp))

        if (isLoading) {
            CircularProgressIndicator()
        }


        movie?.let {
            MovieCard(it)
        }


        Spacer(modifier = Modifier.padding(24.dp))



        Button(onClick = {
            movie?.let {
                movieViewModel.saveMovie(it)
            }
        }) {
            Text("Save Movie to Database")
        }


        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onBack() }
        ) {
            Text("⬅️ Back to Home")
        }
    }
}