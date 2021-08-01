package com.prabin.kagazscanner.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.prabin.kagazscanner.roomdb.entities.AlbumEntity
import com.prabin.kagazscanner.roomdb.entities.ImageEntity

@Dao
interface CameraDao {

    @Insert
    fun insertImage(imageEntity: ImageEntity)

    @Query("SELECT * FROM image_table where albumId =:albumId")
    fun getImages(albumId: Int): LiveData<List<ImageEntity>>

    @Insert
    fun insertAlbum(albumEntity: AlbumEntity)

    @Query("SELECT * FROM album_table")
    fun getAlbums(): LiveData<List<AlbumEntity>>

    @Update
    fun updateAlbumImg(albumEntity: AlbumEntity)
}