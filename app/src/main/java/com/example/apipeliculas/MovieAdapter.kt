package com.example.apipeliculas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

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
        holder.yearTextView.text = "Año: ${movie.Year}"

        // Load image with Glide using fade animation
        Glide.with(holder.itemView.context)
            .load(movie.Poster)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(holder.posterImageView)
    }

    override fun getItemCount() = movies.size
}