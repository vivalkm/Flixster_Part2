package com.vivalkm.flixster_part2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.vivalkm.flixster_part2.databinding.ActivityMainBinding
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "MainActivity/"
private const val SEARCH_API_KEY = BuildConfig.API_KEY
private const val SHOW_SEARCH_URL =
    "https://api.themoviedb.org/3/tv/popular?api_key=${SEARCH_API_KEY}"
private const val MOVIE_SEARCH_URL =
    "https://api.themoviedb.org/3/movie/popular?api_key=${SEARCH_API_KEY}"

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

class MainActivity : AppCompatActivity() {
    private lateinit var popularShowRV: RecyclerView
    private lateinit var popularMovieRv: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        popularShowRV = findViewById(R.id.popularShowRv)
        popularMovieRv = findViewById(R.id.popularMovieRv)

        // Set up ShowAdapter with shows
        val shows = mutableListOf<Show>()
        val showAdapter = ShowAdapter(this, shows)
        popularShowRV.adapter = showAdapter
        popularShowRV.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val clientShow = AsyncHttpClient()
        clientShow.get(SHOW_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch shows: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched shows: $json")
                try {
                    val parsedJson = createJson().decodeFromString(
                        SearchShowsResponse.serializer(),
                        json.jsonObject.toString()
                    )
                    parsedJson.results?.let { list ->
                        shows.addAll(list)
                    }
                    showAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }
        })

        // Set up MovieAdapter with movies
        val movies = mutableListOf<Movie>()
        val movieAdapter = MovieAdapter(this, movies)
        popularMovieRv.adapter = movieAdapter
        popularMovieRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val clientMovie = AsyncHttpClient()
        clientMovie.get(MOVIE_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch movies: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched movies: $json")
                try {
                    val parsedJson = createJson().decodeFromString(
                        SearchMoviesResponse.serializer(),
                        json.jsonObject.toString()
                    )
                    parsedJson.results?.let { list ->
                        movies.addAll(list)
                    }
                    movieAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }
        })
    }
}