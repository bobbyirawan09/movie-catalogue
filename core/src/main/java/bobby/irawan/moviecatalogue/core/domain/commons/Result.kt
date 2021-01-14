package bobby.irawan.moviecatalogue.core.domain.commons

import bobby.irawan.moviecatalogue.core.data.remote.common.ErrorResponse


/**
 * Created by Bobby Irawan on 28/10/20.
 */
sealed class Result<out Data> {
    class Success<T>(val data: T) : Result<T>()

    class Failure(val errorData: ErrorResponse?) : Result<Nothing>()

    sealed class State : Result<Nothing>() {
        object Loading : State()
        object NoInternet : State()
    }

    fun handleResult(
        successDataBlock: (Data) -> Unit = {},
        errorBlock: (ErrorResponse?) -> Unit = {},
        stateBlock: (State) -> Unit = {}
    ) {
        when (this) {
            is Success -> successDataBlock(this.data)
            is Failure -> errorBlock(errorData)
            is State -> stateBlock(this)
        }
    }
}