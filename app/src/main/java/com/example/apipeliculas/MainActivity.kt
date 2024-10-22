package com.example.apipeliculas

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var genreSpinner: Spinner
    private lateinit var yearEditText: EditText  // Añadir EditText para año
    private lateinit var directorEditText: EditText  // Añadir EditText para director
    private lateinit var searchButton: Button  // Añadir botón de búsqueda

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        genreSpinner = findViewById(R.id.genreSpinner)
        yearEditText = findViewById(R.id.yearEditText)  // Inicializar EditText para año
        directorEditText =
            findViewById(R.id.directorEditText)  // Inicializar EditText para director
        searchButton = findViewById(R.id.searchButton)  // Inicializar botón de búsqueda
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Configurar el botón de búsqueda
        searchButton.setOnClickListener {
            searchMovies()
        }
    }

    private fun searchMovies() {
        val query = searchEditText.text.toString()
        val genre = genreSpinner.selectedItem.toString()
        val year = yearEditText.text.toString()
        val director = directorEditText.text.toString()

        val apiService = RetrofitClient.getMovieApiService()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.searchMovies(
                    "bf3b564c",
                    query,
                    genre = if (genre != "All") genre else null,
                    year = if (year != "All") year else null,
                    director = if (director != "All") director else null
                )

                // Comprobar si la respuesta contiene datos
                withContext(Dispatchers.Main) {
                    if (response.Search != null && response.Search.isNotEmpty()) {
                        movieAdapter = MovieAdapter(response.Search)
                        recyclerView.adapter = movieAdapter
                    } else {
                        Toast.makeText(this@MainActivity, "No movies found", Toast.LENGTH_SHORT)
                            .show()

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error al buscar películas: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}