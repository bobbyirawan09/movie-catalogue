package bobby.irawan.moviecatalogue.favorite.ui.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import bobby.irawan.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import bobby.irawan.moviecatalogue.favorite.utils.DataMapper
import kotlinx.coroutines.flow.map

class TvShowFavoriteViewModel(
    private val movieCatalogueUseCase: MovieCatalogueUseCase
) : ViewModel() {

    fun getTvShowFavorites() =
        movieCatalogueUseCase.getFavoriteTvShow().map { favorites ->
            favorites.map {
                DataMapper.favoriteDomainToPresentation(it)
            }
        }.asLiveData()

}