package com.example.cw2.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cw2.MovieResult
import com.example.cw2.model.Movie
import com.example.cw2.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movie = mutableStateOf<Movie?>(null)
    val movie: State<Movie?> = _movie

    private val _movieResults = mutableStateOf<List<MovieResult>>(emptyList())
    val movieResults: State<List<MovieResult>> = _movieResults

    private val _moviesByActor = mutableStateOf<List<Movie>>(emptyList())
    val moviesByActor: State<List<Movie>> = _moviesByActor
    private val _currentScreen = mutableStateOf("home")
    val currentScreen: State<String> = _currentScreen

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    private val _dialogState = mutableStateOf(DialogState())
    val dialogState: State<DialogState> = _dialogState


    private fun updateDialogState(show: Boolean, message: String?, isError: Boolean = false) {
        _dialogState.value = DialogState(show = show, message = message, isError = isError)
    }
    fun resetDialogState() {
        _dialogState.value = DialogState()
    }



    init {
        addHardcodedMovies()
    }

    fun addHardcodedMovies() {
        viewModelScope.launch {
            updateDialogState(show = false, message = null)
            try {
                movieRepository.addHardcodedMovies()
                updateDialogState(show = true, message = "Movies added successfully!")

            } catch (e: Exception) {
                updateDialogState(show = true, message = "Failed to add hardcoded movies: ${e.message}", isError = true)
            }
        }
    }

    fun fetchMovieByTitle(title: String) {
        viewModelScope.launch {
            _isLoading.value = true
            updateDialogState(show = false, message = null)
            try {
                val movieData = movieRepository.getMovieByTitle(title)
                _movie.value = movieData
            } catch (e: Exception) {
                updateDialogState(show = true, message = "Failed to fetch movie: ${e.message}", isError = true)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchMovies(title: String) {
        viewModelScope.launch {
            _isLoading.value = true
            updateDialogState(show = false, message = null)
            try {
                val result = movieRepository.getMoviesBySearchTerm(title)
                _movieResults.value = result

            } catch (e: Exception) {
                updateDialogState(show = true, message = "Failed to fetch movie: ${e.message}", isError = true)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchMoviesByActor(actor: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = movieRepository.getMoviesByActor(actor)
                _moviesByActor.value = result
            } catch (e: Exception) {
                updateDialogState(show = true, message = "Failed to search by actor: ${e.message}", isError = true)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun saveMovie(movie: Movie) {
        viewModelScope.launch {
            _isLoading.value = true
            updateDialogState(show = false, message = null)
            try {
                movieRepository.saveMovie(movie)
                updateDialogState(show = true, message = "Movie saved successfully!")
            } catch (e: Exception) {
                updateDialogState(show = true, message = "Failed to save movie: ${e.message}", isError = true)
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun navigate(screen: String) {
        _currentScreen.value = screen
    }
}