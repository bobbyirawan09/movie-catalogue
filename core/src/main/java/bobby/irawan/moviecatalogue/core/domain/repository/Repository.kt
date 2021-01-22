package bobby.irawan.moviecatalogue.core.domain.repository

import bobby.irawan.moviecatalogue.core.data.common.SimpleResult
import bobby.irawan.moviecatalogue.core.domain.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bobby Irawan on 25/10/20.
 */
interface Repository {
    suspend fun getDetailMovie(movieId: Int): Flow<SimpleResult<MovieDetailDomainModel>>
    suspend fun getDetailTvShow(tvId: Int): Flow<SimpleResult<TvShowDetailDomainModel>>
    suspend fun getPopularMovies(page: Int): Flow<SimpleResult<List<MovieDomainModel>>>
    suspend fun getPopularTvShow(page: Int): Flow<SimpleResult<List<TvShowDomainModel>>>

    fun getAllFavorite(): Flow<List<FavoriteDomainModel>>
    suspend fun getFavoriteById(favoriteId: Int): FavoriteDomainModel?
    fun getFavoriteTvShow(): Flow<List<FavoriteDomainModel>>
    fun getFavoriteMovie(): Flow<List<FavoriteDomainModel>>
    suspend fun addFavorite(favoriteDomainModel: FavoriteDomainModel)
    suspend fun deleteFavorite(favoriteDomainModel: FavoriteDomainModel): Int
}