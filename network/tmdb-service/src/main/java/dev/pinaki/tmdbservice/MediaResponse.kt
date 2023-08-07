package dev.pinaki.tmdbservice

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaResultResponse(
    val page: Int,
    val results: List<MediaResponse>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class MediaResponse(
    val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String?,
    val id: Int,
    val title: String = "",
    @Json(name = "original_language") val originalLanguage: String = "",
    @Json(name = "original_title") val originalTitle: String = "",
    val overview: String = "",
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "media_type") val mediaType: String = "",
    @Json(name = "genre_ids") val genreIds: List<Int> = emptyList(),
    val popularity: Double = 0.0,
    @Json(name = "release_date") val releaseDate: String?,
    val video: Boolean = false,
    @Json(name = "vote_average") val voteAverage: Double = 0.0,
    @Json(name = "vote_count") val voteCount: Int = 0
)