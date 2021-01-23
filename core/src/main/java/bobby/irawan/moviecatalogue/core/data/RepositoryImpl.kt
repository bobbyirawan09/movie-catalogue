package bobby.irawan.moviecatalogue.core.data

import bobby.irawan.moviecatalogue.core.data.common.SimpleResult
import bobby.irawan.moviecatalogue.core.data.local.LocalDataSource
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity
import bobby.irawan.moviecatalogue.core.data.remote.RemoteDataSource
import bobby.irawan.moviecatalogue.core.domain.commons.Result
import bobby.irawan.moviecatalogue.core.domain.commons.mapToResult
import bobby.irawan.moviecatalogue.core.domain.model.*
import bobby.irawan.moviecatalogue.core.domain.repository.Repository
import bobby.irawan.moviecatalogue.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Bobby Irawan on 25/10/20.
 */
class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun getDetailMovie(movieId: Int): Flow<SimpleResult<MovieDetailDomainModel>> {
        return remoteDataSource.getDetailMovie(movieId).mapToResult { response ->
            Result.Success(
                DataMapper.movieDetailResponseToDomain(response)
            )
        }
    }

    override suspend fun getDetailTvShow(tvId: Int): Flow<SimpleResult<TvShowDetailDomainModel>> {
        return remoteDataSource.getDetailTvShow(tvId).mapToResult { response ->
            Result.Success(
                DataMapper.tvShowDetailResponseToDomain(response)
            )
        }
    }

    override suspend fun getPopularMovies(page: Int): Flow<SimpleResult<List<MovieDomainModel>>> {
        return remoteDataSource.getPopularMovies(page).mapToResult { responses ->
            Result.Success(responses?.map { response ->
                DataMapper.movieResponseToDomain(response)
            }.orEmpty())
        }
    }

    override suspend fun getPopularTvShow(page: Int): Flow<SimpleResult<List<TvShowDomainModel>>> {
        return remoteDataSource.getPopularTvShow(page).mapToResult { responses ->
            Result.Success(responses?.map { response ->
                DataMapper.tvShowResponseToDomain(response)
            }.orEmpty())
        }
    }

    override suspend fun getMovieSearchResult(
        query: String,
        page: Int
    ): Flow<SimpleResult<List<MovieDomainModel>>> {
        return remoteDataSource.getMovieSearchResult(query, page).mapToResult { responses ->
            Result.Success(responses?.map { response ->
                DataMapper.movieResponseToDomain(response)
            }.orEmpty())
        }
    }

    override suspend fun getTvShowSearchResult(
        query: String,
        page: Int
    ): Flow<SimpleResult<List<TvShowDomainModel>>> {
        return remoteDataSource.getTvShowSearchResult(query, page).mapToResult { responses ->
            Result.Success(responses?.map { response ->
                DataMapper.tvShowResponseToDomain(response)
            }.orEmpty())
        }
    }

    override fun getAllFavorite(): Flow<List<FavoriteDomainModel>> {
        return localDataSource.getAllFavorite().map { favoriteEntites ->
            favoriteEntites.map { favoriteEntity ->
                DataMapper.favoriteEntityToDomain(favoriteEntity)
            }
        }
    }

    override suspend fun getFavoriteById(favoriteId: Int): FavoriteDomainModel? {
        val favoriteEntity = localDataSource.getFavoriteById(favoriteId)
        return DataMapper.favoriteEntityToDomain(favoriteEntity)
    }

    override fun getFavoriteTvShow(): Flow<List<FavoriteDomainModel>> {
        return localDataSource.getFavoriteTvShow().map { favoriteEntites ->
            favoriteEntites.map { favoriteEntity ->
                DataMapper.favoriteEntityToDomain(favoriteEntity)
            }
        }
    }

    override fun getFavoriteMovie(): Flow<List<FavoriteDomainModel>> {
        return localDataSource.getFavoriteMovie().map { favoriteEntites ->
            favoriteEntites.map { favoriteEntity ->
                DataMapper.favoriteEntityToDomain(favoriteEntity)
            }
        }
    }

    override suspend fun addFavorite(favoriteDomainModel: FavoriteDomainModel) {
        val favoriteEntity = FavoriteEntity.domainToEntity(favoriteDomainModel)
        localDataSource.addFavorite(favoriteEntity)
    }

    override suspend fun deleteFavorite(favoriteDomainModel: FavoriteDomainModel): Int {
        val favoriteEntity = FavoriteEntity.domainToEntity(favoriteDomainModel)
        return localDataSource.deleteFavorite(favoriteEntity)
    }
}