package bobby.irawan.moviecatalogue.core.data.local

import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity
import bobby.irawan.moviecatalogue.core.data.local.room.FavoriteDao
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bobby Irawan on 03/01/21.
 */
class LocalDataSource(private val favoriteDao: FavoriteDao) {

    fun getAllFavorite(): Flow<List<FavoriteEntity>> = favoriteDao.getFavorites()

    suspend fun getFavoriteById(favoriteId: Int): FavoriteEntity? =
        favoriteDao.getFavoriteById(favoriteId)

    fun getFavoriteTvShow(): Flow<List<FavoriteEntity>> = favoriteDao.getFavoriteTvShow()

    fun getFavoriteMovie(): Flow<List<FavoriteEntity>> = favoriteDao.getFavoriteMovie()

    suspend fun addFavorite(favoriteEntity: FavoriteEntity) {
        favoriteDao.addFavorite(favoriteEntity)
    }

    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity): Int =
        favoriteDao.deleteFavorite(favoriteEntity)
}