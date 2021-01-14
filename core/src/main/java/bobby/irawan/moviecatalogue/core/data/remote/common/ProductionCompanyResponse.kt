package bobby.irawan.moviecatalogue.core.data.remote.common

import com.google.gson.annotations.SerializedName

data class ProductionCompanyResponse(
    val id: Int = 0,
    @SerializedName("logo_path")
    val logoPath: String = "",
    val name: String = "",
    @SerializedName("origin_country")
    val originCountry: String = ""
)