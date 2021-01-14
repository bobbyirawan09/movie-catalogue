package bobby.irawan.moviecatalogue.core.data.remote.movie

import bobby.irawan.moviecatalogue.core.data.common.SimpleBaseResponse
import bobby.irawan.moviecatalogue.core.data.common.SimpleResponse
import bobby.irawan.moviecatalogue.core.data.remote.movie.model.MovieDetailResponse
import bobby.irawan.moviecatalogue.core.data.remote.movie.model.MovieResponse
import bobby.irawan.moviecatalogue.core.utils.Constants
import bobby.irawan.moviecatalogue.core.utils.Constants.PATH_PAGE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Bobby Irawan on 25/10/20.
 */
interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query(PATH_PAGE) pages: Int): SimpleBaseResponse<List<MovieResponse>>

    @GET("movie/{${Constants.PATH_MOVIE_ID}}")
    suspend fun getDetailMovie(@Path(Constants.PATH_MOVIE_ID) movieId: Int): SimpleResponse<MovieDetailResponse>
}