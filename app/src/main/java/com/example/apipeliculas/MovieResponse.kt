package com.example.apipeliculas

data class MovieResponse(
    val Search: List<Movie>?,
    val totalResults: String?,
    val Response: String
)
