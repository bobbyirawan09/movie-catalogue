package bobby.irawan.moviecatalogue.core.data.common

import bobby.irawan.moviecatalogue.core.data.remote.common.ErrorResponse

/**
 * Created by Bobby Irawan on 22/09/20.
 */

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val error: ErrorResponse?,
    val message: String? = ""
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null,
            )
        }

        fun <T> error(error: ErrorResponse, data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                error
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}