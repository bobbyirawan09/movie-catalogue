package bobby.irawan.moviecatalogue.core.domain.usecase

import bobby.irawan.moviecatalogue.core.data.common.SimpleResult
import bobby.irawan.moviecatalogue.core.domain.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bobby Irawan on 03/01/21.
 */
interface MovieCatalogueUseCase {

    //List
    suspend fun getPopularMovies(page: Int): Flow<SimpleResult<List<MovieDomainModel>>>
    suspend fun getPopularTvShow(page: Int): Flow<SimpleResult<List<TvShowDomainModel>>>

    //Detail
    suspend fun getDetailMovie(movieId: Int): Flow<SimpleResult<MovieDetailDomainModel>>
    suspend fun getDetailTvShow(tvId: Int): Flow<SimpleResult<TvShowDetailDomainModel>>

    //Favorite
    fun getFavoriteMovie(choice: Int): Flow<List<FavoriteDomainModel>>
    fun getFavoriteTvShow(choice: Int): Flow<List<FavoriteDomainModel>>
    fun getAllFavorite(): Flow<List<FavoriteDomainModel>>
    suspend fun getFavoriteById(favoriteId: Int): FavoriteDomainModel?
    suspend fun addFavorite(favoriteDomainModel: FavoriteDomainModel)
    suspend fun deleteFavorite(favoriteDomainModel: FavoriteDomainModel): Int
}