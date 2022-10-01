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

const val SHOW_EXTRA = "SHOW_EXTRA"
private const val TAG = "ShowAdapter"
private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

class ShowAdapter(private val context: Context, private val shows: MutableList<Show>) : RecyclerView.Adapter<ShowAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.show_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the individual article and bind to holder
        val article = shows[position]
        holder.bind(article)
    }

    override fun getItemCount() = shows.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val posterImageView = itemView.findViewById<ImageView>(R.id.posterImageView)
        private val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)

        init {
            itemView.setOnClickListener(this)
        }

        // a helper method to help set up the onBindViewHolder method
        fun bind(show: Show) {
            nameTextView.text = show.name

            Glide.with(context)
                .load(IMAGE_BASE_URL + show.poster)
                .into(posterImageView)
        }

        override fun onClick(v: View?) {
            // Get selected show
            val show = shows[absoluteAdapterPosition]

            // Navigate to Details screen and pass selected show
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(SHOW_EXTRA, show)
            context.startActivity(intent)
        }
    }
}
