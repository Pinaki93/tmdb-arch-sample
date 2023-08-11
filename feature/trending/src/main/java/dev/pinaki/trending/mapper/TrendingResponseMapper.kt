package dev.pinaki.trending.mapper

import dev.pinaki.arch.Mapper
import dev.pinaki.model.Media
import dev.pinaki.model.MediaResult
import dev.pinaki.tmdbservice.MediaResponse
import dev.pinaki.tmdbservice.MediaResultResponse
import javax.inject.Inject

class TrendingResponseMapper @Inject constructor() :
    Mapper<MediaResultResponse, MediaResult> {
    override fun map(input: MediaResultResponse): MediaResult {
        return with(input) {
            MediaResult(
                page = page,
                results = results.mapToDomain(),
                totalPages = totalPages,
                totalResults = totalResults,
            )
        }
    }

    private fun List<MediaResponse>.mapToDomain(): List<Media> {
        return map { result ->
            with(result) {
                Media(
                    adult,
                    backdropPath,
                    id,
                    title.ifBlank { name },
                    originalLanguage,
                    originalTitle,
                    overview,
                    posterPath,
                    mediaType,
                    genreIds,
                    popularity,
                    releaseDate,
                    video,
                    voteAverage,
                    voteCount,
                )
            }
        }
    }
}