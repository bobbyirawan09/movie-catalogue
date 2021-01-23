package bobby.irawan.moviecatalogue.core.data.remote

import bobby.irawan.moviecatalogue.core.data.common.Resource
import bobby.irawan.moviecatalogue.core.data.common.networkBaseResponseHandling
import bobby.irawan.moviecatalogue.core.data.common.networkHandling
import bobby.irawan.moviecatalogue.core.data.remote.movie.MovieApi
import bobby.irawan.moviecatalogue.core.data.remote.movie.model.MovieDetailResponse
import bobby.irawan.moviecatalogue.core.data.remote.movie.model.MovieResponse
import bobby.irawan.moviecatalogue.core.data.remote.tvshow.TvShowApi
import bobby.irawan.moviecatalogue.core.data.remote.tvshow.model.TvShowDetailResponse
import bobby.irawan.moviecatalogue.core.data.remote.tvshow.model.TvShowResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bobby Irawan on 03/01/21.
 */
class RemoteDataSource(private val tvShowApi: TvShowApi, private val movieApi: MovieApi) {
    suspend fun getDetailMovie(movieId: Int): Flow<Resource<MovieDetailResponse>> {
        return networkHandling(
            callApi = {
                movieApi.getDetailMovie(movieId)
            }
        )
    }

    suspend fun getDetailTvShow(tvId: Int): Flow<Resource<TvShowDetailResponse>> {
        return networkHandling(
            callApi = {
                tvShowApi.getDetailTvShow(tvId)
            }
        )
    }

    suspend fun getPopularMovies(page: Int): Flow<Resource<List<MovieResponse>>> {
        return networkBaseResponseHandling(
            callApi = {
                movieApi.getPopularMovies(page)
            }
        )
    }

    suspend fun getPopularTvShow(page: Int): Flow<Resource<List<TvShowResponse>>> {
        return networkBaseResponseHandling(
            callApi = {
                tvShowApi.getPopularTvShow(page)
            }
        )
    }

    suspend fun getMovieSearchResult(query: String, page: Int): Flow<Resource<List<MovieResponse>>> {
        return networkBaseResponseHandling(
            callApi = {
                movieApi.getMovieSearchResult(query, page)
            }
        )
    }

    suspend fun getTvShowSearchResult(query: String,page: Int): Flow<Resource<List<TvShowResponse>>> {
        return networkBaseResponseHandling(
            callApi = {
                tvShowApi.getTvShowSearchResult(query, page)
            }
        )
    }
}