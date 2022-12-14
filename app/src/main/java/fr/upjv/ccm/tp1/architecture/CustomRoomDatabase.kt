package fr.upjv.ccm.tp1.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.upjv.ccm.tp1.dao.BoardgameDao
import fr.upjv.ccm.tp1.model.LocalDataSourceSample

@Database(
    entities = [
        LocalDataSourceSample::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {

    abstract fun BoardgameDao(): BoardgameDao
}

