package com.example.movie.model

data class MovieSearchResponse (
    val search: List<Movie>?,
    val totalResults: String?,
    val Response: String?
)