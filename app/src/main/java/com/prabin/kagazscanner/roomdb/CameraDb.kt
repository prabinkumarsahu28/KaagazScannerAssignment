package com.prabin.kagazscanner.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prabin.kagazscanner.roomdb.entities.AlbumEntity
import com.prabin.kagazscanner.roomdb.entities.ImageEntity

@Database(entities = [ImageEntity::class, AlbumEntity::class], version = 1)
abstract class CameraDb : RoomDatabase() {
    abstract fun getCameraDao():CameraDao

    companion object {
        private var database: CameraDb? = null

        fun getInstance(context: Context): CameraDb {
            return if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    CameraDb::class.java,
                    "cameraDB"
                ).fallbackToDestructiveMigration().build()
                database!!
            } else {
                database!!
            }
        }
    }
}