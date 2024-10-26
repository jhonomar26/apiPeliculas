package com.example.apipeliculas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    private lateinit var genreAutoCompleteTextView: AutoCompleteTextView  // Cambiado a AutoCompleteTextView
    private lateinit var yearEditText: EditText
    private lateinit var directorEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var aboutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        genreAutoCompleteTextView = findViewById(R.id.genreSpinner)
        yearEditText = findViewById(R.id.yearEditText)
        directorEditText = findViewById(R.id.directorEditText)
        searchButton = findViewById(R.id.searchButton)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        aboutButton = findViewById(R.id.aboutButton)

        // Lista de géneros con internacionalización
        val types = listOf(
            getString(R.string.type_all),
            getString(R.string.type_movie),
            getString(R.string.type_series),
            getString(R.string.type_episode)
        )

        // Configura el adaptador para el AutoCompleteTextView
        val genreAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, types)
        genreAutoCompleteTextView.setAdapter(genreAdapter)

        // Configurar el botón de búsqueda
        searchButton.setOnClickListener {
            searchMovies()
        }

        aboutButton.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

    }


    private fun searchMovies() {
        val query = searchEditText.text.toString()
        val types =
            genreAutoCompleteTextView.text.toString()  // Obtener texto del AutoCompleteTextView
        val year = yearEditText.text.toString()
        val director = directorEditText.text.toString()

        val apiService = RetrofitClient.getMovieApiService()
        val apiKey = getString(R.string.movie_api_key)


        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.searchMovies(
                    apiKey,
                    query,
                    //null, es no tener en cuenta ese atributo
                    type = if (types != "All" && types != "Todo") types else null,
                    year = if (year.isNotBlank()) year else null,
                    director = if (director.isNotBlank()) director else null
                )
                //Actualizacion de la interfaz de usuario

                withContext(Dispatchers.Main) {
                    if (response.Search != null && response.Search.isNotEmpty()) {
                        Log.e("", "" + response.Search)
                        movieAdapter = MovieAdapter(response.Search)
                        recyclerView.adapter = movieAdapter
                    } else {
                        Toast.makeText(this@MainActivity, "No movies found", Toast.LENGTH_SHORT)
                            .show()
                        // Limpia el RecyclerView
                        movieAdapter.updateMovies(emptyList())
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
