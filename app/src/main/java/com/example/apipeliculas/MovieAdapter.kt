package com.example.apipeliculas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
//Mostrar la lista de peliculas
class MovieAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.movieTitle)
        val yearTextView: TextView = itemView.findViewById(R.id.movieYear)
        val posterImageView: ImageView = itemView.findViewById(R.id.moviePoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.titleTextView.text = movie.Title
        holder.yearTextView.text = movie.Year
        Glide.with(holder.itemView.context)
            .load(movie.Poster)
            .into(holder.posterImageView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }



}
