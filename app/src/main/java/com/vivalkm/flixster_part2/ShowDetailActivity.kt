package com.vivalkm.flixster_part2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "ShowDetailActivity"
private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

class ShowDetailActivity : AppCompatActivity() {
    private lateinit var backdropImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var voteTextView: TextView
    private lateinit var overviewTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Find the views for the screen
        backdropImageView = findViewById(R.id.backdropImageView)
        nameTextView = findViewById(R.id.nameTextView)
        voteTextView = findViewById(R.id.voteTextView)
        overviewTextView = findViewById(R.id.overviewTextView)

        // Get the extra from the Intent
        val show = intent.getSerializableExtra(SHOW_EXTRA) as Show

        // Set the title, vote, and overview information from the show
        nameTextView.text = show.name
        voteTextView.text = show.vote_average
        overviewTextView.text = show.overview

        // Load the media image
        Glide.with(this)
            .load(IMAGE_BASE_URL + show.backdrop)
            .into(backdropImageView)

        var btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener() {
            finish()
        }
    }
}