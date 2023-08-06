package dev.pinaki.tmdbservice

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaResponse(
    val page: Int,
    val results: List<Media>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class Media(
    val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String?,
    val id: Int,
    val title: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    val overview: String,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "media_type") val mediaType: String,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    val popularity: Double,
    @Json(name = "release_date") val releaseDate: String?,
    val video: Boolean,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int
) {
    fun getPosterUrl() = "https://image.tmdb.org/t/p/w500$posterPath"
    fun getBackdropUrl() = "https://image.tmdb.org/t/p/w500$backdropPath"
}