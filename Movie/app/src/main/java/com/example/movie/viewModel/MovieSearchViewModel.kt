/*
package com.example.movie.viewModel

import androidx.lifecycle.ViewModel
import com.example.movie.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieSearchViewModel : ViewModel() {
    private val queryTextFlow = MutableStateFlow("")
    var queryText = queryTextFlow.asStateFlow()

    private val moviesStateFlow = MutableStateFlow<List<Movie>>(listOf())
    var moviesState = moviesStateFlow.asStateFlow()

    fun updateMovies(movies: List<Movie>) {
        moviesStateFlow.update { movies }
    }
}
 */