package bobby.irawan.moviecatalogue.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import bobby.irawan.moviecatalogue.core.data.local.entity.FavoriteEntity

/**
 * Created by Bobby Irawan on 17/11/20.
 */
@Database(entities = arrayOf(FavoriteEntity::class), version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}