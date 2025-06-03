package com.example.cw2.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.example.cw2.repository.MovieRepositoryImpl
import com.example.cw2.viewmodel.MovieViewModel
import com.example.cw2.viewmodel.MovieViewModelFactory

@Composable
fun HomeScreen(
    onNavigate: () -> Unit,
    onNavigateActors: () -> Unit,
    onNavigateMoviesCase: () -> Unit
) {
    val context = LocalContext.current
    val movieDao = AppDatabase.getDatabase(context).movieDao()
    val repository = MovieRepositoryImpl(movieDao)
    val movieViewModel: MovieViewModel = viewModel(factory = MovieViewModelFactory(repository))
    val error by movieViewModel.error
    var showErrorDialog by rememberSaveable { mutableStateOf(false) }
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                movieViewModel.addHardcodedMovies()
            }
        ) {
            Text("Add Movie to Database")
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick = {
            onNavigate()
        }) {
            Text("Search for Movies")
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick = {
            onNavigateActors()
        }) {
            Text("Search for Actors")
        }
        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick = {
            onNavigateMoviesCase()
        }) {
            Text("Search all Movies ")
        }
        if (error != null) {
            LaunchedEffect(error) {
                showErrorDialog = true
            }
        }

    }
}