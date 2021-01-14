package bobby.irawan.moviecatalogue.core.data.common

import bobby.irawan.moviecatalogue.core.data.remote.common.BaseResponse
import bobby.irawan.moviecatalogue.core.domain.commons.Result
import retrofit2.Response

/**
 * Created by Bobby Irawan on 28/10/20.
 */

typealias SimpleResult<T> = Result<T>

typealias SimpleBaseResponse<T> = Response<BaseResponse<T>>

typealias SimpleResponse<T> = Response<T>