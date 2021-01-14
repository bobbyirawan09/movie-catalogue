package bobby.irawan.moviecatalogue.core.domain.model

data class SeasonDomainModel(
    var id: Int,
    var title: String,
    var episodeCount: Int,
    var airDate: String,
    var overview: String,
    var posterUrl: String,
    var seasonNumber: Int
)
