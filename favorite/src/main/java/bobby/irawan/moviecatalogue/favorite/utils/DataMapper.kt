package bobby.irawan.moviecatalogue.favorite.utils

import bobby.irawan.moviecatalogue.core.domain.model.FavoriteDomainModel
import bobby.irawan.moviecatalogue.favorite.model.FavoriteModelView

object DataMapper {

    fun favoriteDomainToPresentation(domainModel: FavoriteDomainModel) =
        FavoriteModelView(
            domainModel.id,
            domainModel.title,
            domainModel.releaseDate,
            domainModel.posterUrl,
            domainModel.overview,
            domainModel.voteCount,
            domainModel.voteAverage
        )

}