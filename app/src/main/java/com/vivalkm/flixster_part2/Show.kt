package com.vivalkm.flixster_part2

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SearchShowsResponse(
    @SerialName("results")
    val results: List<Show>
)


@Keep
@Serializable
class Show(
    @SerialName("poster_path")
    val poster: String?,
    @SerialName("backdrop_path")
    val backdrop: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("vote_average")
    val vote_average: String?
) : java.io.Serializable {}