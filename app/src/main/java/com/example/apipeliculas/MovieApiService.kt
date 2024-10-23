package com.example.apipeliculas

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("/")
    //Obtener informacion o recursos especificos de un servidor
    // Esta función puede ser llamada como una coroutina
    suspend fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") query: String,  // Búsqueda por título
        @Query("type") type: String? = null,  // Tipo (movie, series, etc.)
        @Query("y") year: String? = null,  // Filtro por año
        @Query("r") responseFormat: String = "json", // Formato de respuesta (por defecto json)
        @Query("director") director: String? = null  // Filtro por director
    ): MovieResponse
}
