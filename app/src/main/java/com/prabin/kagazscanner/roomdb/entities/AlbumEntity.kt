package com.prabin.kagazscanner.roomdb.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_table")
data class AlbumEntity(

    @ColumnInfo(name = "albumName")
    var albumName: String,

    @ColumnInfo(name = "albumImg")
    var albumImg: String

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var albumId: Int? = null
}