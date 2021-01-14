package bobby.irawan.moviecatalogue.core.data.remote.common

/**
 * Created by Bobby Irawan on 25/10/20.
 */
data class BaseResponse<T>(
    val results: T? = null
)