package fr.upjv.ccm.tp1.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.upjv.ccm.tp1.model.LocalDataSourceSample

@Dao
interface BoardgameDao {

    @Query("SELECT * FROM boardgame_object_table ORDER BY name ASC")
    fun selectAll(): LiveData<List<LocalDataSourceSample>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(androidVersion: LocalDataSourceSample)

    @Query("DELETE FROM boardgame_object_table")
    fun deleteAll()
}
