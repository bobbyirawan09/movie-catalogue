package bobby.irawan.moviecatalogue.core.data.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity.Companion.COLUMN_ID
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity.Companion.DATABASE_TABLE_FAVORITE
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

    @RawQuery(observedEntities = [FavoriteEntity::class])
    fun getFavoriteTvShow(query: SupportSQLiteQuery): Flow<List<FavoriteEntity>>

    @RawQuery(observedEntities = [FavoriteEntity::class])
    fun getFavoriteMovie(query: SupportSQLiteQuery): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favouriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity): Int
}