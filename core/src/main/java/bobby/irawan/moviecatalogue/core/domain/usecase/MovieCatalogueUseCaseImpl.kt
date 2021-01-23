package bobby.irawan.moviecatalogue.core.domain.usecase

import bobby.irawan.moviecatalogue.core.data.common.SimpleResult
import bobby.irawan.moviecatalogue.core.domain.model.*
import bobby.irawan.moviecatalogue.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bobby Irawan on 03/01/21.
 */
class MovieCatalogueUseCaseImpl(private val repository: Repository) : MovieCatalogueUseCase {
    override suspend fun getPopularMovies(page: Int): Flow<SimpleResult<List<MovieDomainModel>>> =
        repository.getPopularMovies(page)

    override suspend fun getPopularTvShow(page: Int): Flow<SimpleResult<List<TvShowDomainModel>>> =
        repository.getPopularTvShow(page)

    override suspend fun getDetailMovie(movieId: Int): Flow<SimpleResult<MovieDetailDomainModel>> =
        repository.getDetailMovie(movieId)

    override suspend fun getDetailTvShow(tvId: Int): Flow<SimpleResult<TvShowDetailDomainModel>> =
        repository.getDetailTvShow(tvId)

    override suspend fun getMovieSearchResult(
        query: String,
        page: Int
    ): Flow<SimpleResult<List<MovieDomainModel>>> = repository.getMovieSearchResult(query, page)

    override suspend fun getTvShowSearchResult(
        query: String,
        page: Int
    ): Flow<SimpleResult<List<TvShowDomainModel>>> = repository.getTvShowSearchResult(query, page)

    override fun getFavoriteMovie(): Flow<List<FavoriteDomainModel>> =
        repository.getFavoriteMovie()

    override fun getFavoriteTvShow(): Flow<List<FavoriteDomainModel>> =
        repository.getFavoriteTvShow()

    override fun getAllFavorite(): Flow<List<FavoriteDomainModel>> =
        repository.getAllFavorite()

    override suspend fun getFavoriteById(favoriteId: Int): FavoriteDomainModel? =
        repository.getFavoriteById(favoriteId)

    override suspend fun addFavorite(favoriteDomainModel: FavoriteDomainModel) =
        repository.addFavorite(favoriteDomainModel)

    override suspend fun deleteFavorite(favoriteDomainModel: FavoriteDomainModel): Int =
        repository.deleteFavorite(favoriteDomainModel)


}