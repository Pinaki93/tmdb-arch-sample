package dev.pinaki.model

data class MediaResult(
    val page: Int,
    val results: List<Media>,
    val totalPages: Int,
    val totalResults: Int
)

data class Media(
    val adult: Boolean,
    val backdropPath: String?,
    val id: Int,
    val title: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String?,
    val mediaType: String,
    val genreIds: List<Int>,
    val popularity: Double,
    val releaseDate: String?,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) {
    fun posterUrl() = "https://image.tmdb.org/t/p/w500$posterPath"
    fun backdropUrl() = "https://image.tmdb.org/t/p/w500$backdropPath"
}