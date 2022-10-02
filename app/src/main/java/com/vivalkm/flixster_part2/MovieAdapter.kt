package com.vivalkm.flixster_part2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

const val MOVIE_EXTRA = "MOVIE_EXTRA"
private const val TAG = "MovieAdapter"
private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

class MovieAdapter(private val context: Context, private val movies: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the individual movie and bind to holder
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val posterImageView = itemView.findViewById<ImageView>(R.id.posterImageView)
        private val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)

        init {
            itemView.setOnClickListener(this)
        }

        // a helper method to help set up the onBindViewHolder method
        fun bind(movie: Movie) {
            nameTextView.text = movie.title

            Glide.with(context)
                .load(IMAGE_BASE_URL + movie.poster)
                .placeholder(R.drawable.loading)
                .transform(RoundedCorners(40))
                .into(posterImageView)
        }

        override fun onClick(v: View?) {
            // Get selected movie
            val movie = movies[absoluteAdapterPosition]

            // Navigate to Details screen and pass selected movie
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }
}
