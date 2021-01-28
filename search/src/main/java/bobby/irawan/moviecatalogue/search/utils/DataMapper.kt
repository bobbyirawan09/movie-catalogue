package bobby.irawan.moviecatalogue.search.utils

import bobby.irawan.moviecatalogue.core.domain.model.SearchDomainModel
import bobby.irawan.moviecatalogue.search.model.SearchModelView

/**
 * Created by Bobby Irawan on 28/01/21.
 */
object DataMapper {
    fun searchDomainToPresentation(domainModel: SearchDomainModel) =
        SearchModelView(
            id = domainModel.id,
            title = domainModel.title,
            releaseDate = domainModel.releaseDate,
            posterUrl = domainModel.posterUrl,
            backdropUrl = domainModel.backdropUrl,
            voteAverage = domainModel.voteAverage,
            voteCount = domainModel.voteCount
        )
}