package bobby.irawan.moviecatalogue.core.data.remote.tvshow

import bobby.irawan.moviecatalogue.core.data.common.SimpleBaseResponse
import bobby.irawan.moviecatalogue.core.data.common.SimpleResponse
import bobby.irawan.moviecatalogue.core.data.remote.tvshow.model.TvShowDetailResponse
import bobby.irawan.moviecatalogue.core.data.remote.tvshow.model.TvShowResponse
import bobby.irawan.moviecatalogue.core.utils.Constants.PATH_PAGE
import bobby.irawan.moviecatalogue.core.utils.Constants.PATH_TV_ID
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Bobby Irawan on 25/10/20.
 */
interface TvShowApi {

    @GET("tv/popular")
    suspend fun getPopularTvShow(
        @Query(PATH_PAGE) pages: Int
    ): SimpleBaseResponse<List<TvShowResponse>>

    @GET("tv/{$PATH_TV_ID}")
    suspend fun getDetailTvShow(
        @Path(PATH_TV_ID) tvId: Int
    ): SimpleResponse<TvShowDetailResponse>
}