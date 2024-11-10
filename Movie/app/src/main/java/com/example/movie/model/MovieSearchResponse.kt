package com.example.movie.model

data class MovieSearchResponse (
    val Search: List<Movie>,
    val totalResults: String,
    val Response: String
)