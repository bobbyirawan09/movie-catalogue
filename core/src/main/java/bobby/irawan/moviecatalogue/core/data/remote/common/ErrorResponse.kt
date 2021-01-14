package bobby.irawan.moviecatalogue.core.data.remote.common

import bobby.irawan.moviecatalogue.core.data.common.ErrorType
import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status_code")
    val code: Int? = 400,
    @SerializedName("status_message")
    val message: String? = "",
    val type: ErrorType?
)