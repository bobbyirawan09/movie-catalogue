package bobby.irawan.moviecatalogue.core.data.local.room

import androidx.room.*
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity.Companion.COLUMN_ID
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity.Companion.COLUMN_TYPE
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity.Companion.DATABASE_TABLE_FAVORITE
import bobby.irawan.moviecatalogue.core.utils.Constants.ITEM_MOVIE
import bobby.irawan.moviecatalogue.core.utils.Constants.ITEM_TV_SHOW
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bobby Irawan on 17/11/20.
 */

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM $DATABASE_TABLE_FAVORITE")
    fun getFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM $DATABASE_TABLE_FAVORITE WHERE $COLUMN_ID = :favoriteId")
    suspend fun getFavoriteById(favoriteId: Int): FavoriteEntity?

    @Query("SELECT * FROM $DATABASE_TABLE_FAVORITE WHERE $COLUMN_TYPE LIKE '%$ITEM_TV_SHOW%'")
    fun getFavoriteTvShow(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM $DATABASE_TABLE_FAVORITE WHERE $COLUMN_TYPE LIKE '%$ITEM_MOVIE%'")
    fun getFavoriteMovie(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favouriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity): Int
}