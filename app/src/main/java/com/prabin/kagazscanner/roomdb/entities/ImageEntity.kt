package com.prabin.kagazscanner.roomdb.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "image_table")
data class ImageEntity(

    @ColumnInfo(name = "albumId")
    var albumId: Long,

    @ColumnInfo(name = "imgName")
    var imgName: String,

    @ColumnInfo(name = "imgLoc")
    var imgLoc: String,

    @ColumnInfo(name = "timeStamp")
    var timeStamp: Long

) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}