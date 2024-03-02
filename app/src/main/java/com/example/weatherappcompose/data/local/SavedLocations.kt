package com.example.weatherappcompose.data.locale

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class SavedLocations(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "locationId")
    var locationId: Int = 0,

    @ColumnInfo(name = "savedLocationName")
    var savedLocationName: String,
)
