package bobby.irawan.moviecatalogue.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity.Companion.DATABASE_TABLE_FAVORITE
import bobby.irawan.moviecatalogue.core.domain.model.FavoriteDomainModel

/**
 * Created by Bobby Irawan on 17/11/20.
 */
@Entity(tableName = DATABASE_TABLE_FAVORITE)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = COLUMN_ID)
    var id: Int = 0,
    @ColumnInfo(name = COLUMN_TITLE)
    var title: String = "",
    @ColumnInfo(name = COLUMN_RELEASE_DATE)
    var releaseDate: String = "",
    @ColumnInfo(name = COLUMN_POSTER_URL)
    var posterUrl: String = "",
    @ColumnInfo(name = COLUMN_BACKDROP_URL)
    var backdropUrl: String = "",
    @ColumnInfo(name = COLUMN_OVERVIEW)
    var overview: String = "",
    @ColumnInfo(name = COLUMN_VOTE_COUNT)
    var voteCount: Int = 0,
    @ColumnInfo(name = COLUMN_VOTE_AVERAGE)
    var voteAverage: Double = 0.0,
    @ColumnInfo(name = COLUMN_TYPE)
    var type: String = ""
) {
    companion object {
        const val DATABASE_TABLE_FAVORITE = "favorite"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_RELEASE_DATE = "release_date"
        const val COLUMN_POSTER_URL = "poster_url"
        const val COLUMN_BACKDROP_URL = "backdrop_url"
        const val COLUMN_OVERVIEW = "overview"
        const val COLUMN_VOTE_COUNT = "vote_count"
        const val COLUMN_VOTE_AVERAGE = "vote_average"
        const val COLUMN_TYPE = "type"

        fun domainToEntity(favoriteDomainModel: FavoriteDomainModel) = FavoriteEntity(
            favoriteDomainModel.id,
            favoriteDomainModel.title,
            favoriteDomainModel.releaseDate,
            favoriteDomainModel.posterUrl,
            favoriteDomainModel.backdropUrl,
            favoriteDomainModel.overview,
            favoriteDomainModel.voteCount,
            favoriteDomainModel.voteAverage,
            favoriteDomainModel.type
        )
    }
}