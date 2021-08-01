package com.prabin.kagazscanner.roomdb

import androidx.lifecycle.LiveData
import com.prabin.kagazscanner.roomdb.entities.AlbumEntity
import com.prabin.kagazscanner.roomdb.entities.ImageEntity

class CameraRepo(private val cameraDao: CameraDao) {

    fun insertImage(imageEntity: ImageEntity) {
        cameraDao.insertImage(imageEntity)
    }

    fun getImages(albumId: Long): LiveData<List<ImageEntity>> {
        return cameraDao.getImages(albumId)
    }

    fun insertAlbum(albumEntity: AlbumEntity): Long {
        return cameraDao.insertAlbum(albumEntity)
    }

    fun getAlbums(): LiveData<List<AlbumEntity>> {
        return cameraDao.getAlbums()
    }

    fun updateAlbumImg(albumEntity: AlbumEntity){
        cameraDao.updateAlbumImg(albumEntity)
    }
}