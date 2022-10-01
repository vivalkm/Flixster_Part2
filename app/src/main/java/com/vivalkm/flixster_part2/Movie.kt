package com.vivalkm.flixster_part2

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SearchMoviesResponse(
    @SerialName("results")
    val results: List<Movie>
)


@Keep
@Serializable
class Movie(
    @SerialName("poster_path")
    val poster: String?,
    @SerialName("backdrop_path")
    val backdrop: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("vote_average")
    val vote_average: String?
) : java.io.Serializable {}