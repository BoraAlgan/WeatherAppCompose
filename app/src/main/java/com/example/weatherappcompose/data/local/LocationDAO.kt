package com.example.weatherappcompose.data.locale

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface LocationDAO {
    @Insert
    suspend fun insert (location: SavedLocations)

    @Delete
    suspend fun delete (locations: SavedLocations)

    @Query("SELECT * FROM location")
    suspend fun getAll() : List<SavedLocations>
}