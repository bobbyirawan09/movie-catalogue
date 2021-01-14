package bobby.irawan.moviecatalogue.core.domain.commons

import bobby.irawan.moviecatalogue.core.BuildConfig.BASE_IMG_URL
import bobby.irawan.moviecatalogue.core.data.common.ErrorType
import bobby.irawan.moviecatalogue.core.data.common.Resource
import bobby.irawan.moviecatalogue.core.utils.Constants.DEFAULT_BACKDROP_URL
import bobby.irawan.moviecatalogue.core.utils.Constants.DEFAULT_POSTER_URL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Bobby Irawan on 28/10/20.
 */
fun String?.asPosterUrl() =
    if (this.isNullOrEmpty()) DEFAULT_POSTER_URL else BASE_IMG_URL + "w342" + this

fun String?.asBackdropUrl() =
    if (this.isNullOrEmpty()) DEFAULT_BACKDROP_URL else BASE_IMG_URL + "w780" + this

suspend fun <Data, MappedData> Flow<Resource<Data>>.mapToResult(
    map: (data: Data?) -> Result<MappedData>
): Flow<Result<MappedData>> = flow {
    this@mapToResult.collect { resourceData ->
        when (resourceData.status) {
            Resource.Status.SUCCESS -> {
                emit(map(resourceData.data))
            }
            Resource.Status.ERROR -> {
                when (resourceData.error?.type) {
                    ErrorType.NO_INTERNET -> emit(Result.State.NoInternet)
                    else -> emit(Result.Failure(resourceData.error))
                }
            }
            Resource.Status.LOADING -> {
                emit(Result.State.Loading)
            }
        }
    }
}

fun String?.asShowDate(): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        formatter.format(parser.parse(this.orEmpty()) ?: Date())
    } catch (e: Exception) {
        ""
    }
}

fun String?.asReleaseYear(): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("yyyy", Locale.getDefault())
        formatter.format(parser.parse(this) ?: Date())
    } catch (e: Exception) {
        ""
    }
}