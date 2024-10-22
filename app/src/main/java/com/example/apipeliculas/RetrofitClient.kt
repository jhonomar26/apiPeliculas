package com.example.apipeliculas

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//Hacer una instancia de retrofit
//Hacer solicitudes de tipo http
//Solo existe una instancia en tiempo de ejecución
object RetrofitClient {
    private const val BASE_URL = "https://www.omdbapi.com/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getMovieApiService(): MovieApiService {
        return getRetrofit().create(MovieApiService::class.java)
    }
}
