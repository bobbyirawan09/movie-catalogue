package bobby.irawan.moviecatalogue.core.data.local

import androidx.sqlite.db.SimpleSQLiteQuery
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity.Companion.COLUMN_TITLE
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity.Companion.COLUMN_TYPE
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity.Companion.COLUMN_VOTE_AVERAGE
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity.Companion.DATABASE_TABLE_FAVORITE
import bobby.irawan.moviecatalogue.core.data.local.room.FavoriteDao
import bobby.irawan.moviecatalogue.core.utils.Constants.ITEM_MOVIE
import bobby.irawan.moviecatalogue.core.utils.Constants.ITEM_TV_SHOW
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bobby Irawan on 03/01/21.
 */
class LocalDataSource(private val favoriteDao: FavoriteDao) {

    fun getAllFavorite(): Flow<List<FavoriteEntity>> = favoriteDao.getFavorites()

    suspend fun getFavoriteById(favoriteId: Int): FavoriteEntity? =
        favoriteDao.getFavoriteById(favoriteId)

    fun getFavoriteTvShow(choice: Int): Flow<List<FavoriteEntity>> {
        val rawQuery = generateRawQuerySort(choice, ITEM_TV_SHOW)
        return favoriteDao.getFavoriteTvShow(rawQuery)
    }

    fun getFavoriteMovie(choice: Int): Flow<List<FavoriteEntity>> {
        val rawQuery = generateRawQuerySort(choice, ITEM_MOVIE)
        return favoriteDao.getFavoriteMovie(rawQuery)
    }

    private fun generateRawQuerySort(choice: Int, type: String): SimpleSQLiteQuery {
        val query = when (choice) {
            1 ->
                "SELECT * FROM $DATABASE_TABLE_FAVORITE WHERE $COLUMN_TYPE LIKE '%$type%' ORDER BY $COLUMN_TITLE ASC"
            2 ->
                "SELECT * FROM $DATABASE_TABLE_FAVORITE WHERE $COLUMN_TYPE LIKE '%$type%' ORDER BY $COLUMN_TITLE DESC"
            3 ->
                "SELECT * FROM $DATABASE_TABLE_FAVORITE WHERE $COLUMN_TYPE LIKE '%$type%' ORDER BY $COLUMN_VOTE_AVERAGE DESC"
            4 ->
                "SELECT * FROM $DATABASE_TABLE_FAVORITE WHERE $COLUMN_TYPE LIKE '%$type%' ORDER BY $COLUMN_VOTE_AVERAGE ASC"
            else -> "SELECT * FROM $DATABASE_TABLE_FAVORITE WHERE $COLUMN_TYPE LIKE '%$type%' ORDER BY $COLUMN_TITLE ASC"
        }
        return SimpleSQLiteQuery(query)
    }

    suspend fun addFavorite(favoriteEntity: FavoriteEntity) {
        favoriteDao.addFavorite(favoriteEntity)
    }

    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity): Int =
        favoriteDao.deleteFavorite(favoriteEntity)
}