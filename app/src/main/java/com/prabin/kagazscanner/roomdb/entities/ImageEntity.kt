package com.prabin.kagazscanner.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
data class ImageEntity(

    @ColumnInfo(name = "albumId")
    var albumId: Int,

    @ColumnInfo(name = "imgName")
    var imgName: String,

    @ColumnInfo(name = "imgLoc")
    var imgLoc: String,

    @ColumnInfo(name = "timeStamp")
    var timeStamp: Long

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}