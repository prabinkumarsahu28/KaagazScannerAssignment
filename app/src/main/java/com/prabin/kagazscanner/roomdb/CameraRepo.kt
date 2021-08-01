package com.prabin.kagazscanner.roomdb

import androidx.lifecycle.LiveData
import com.prabin.kagazscanner.roomdb.entities.ImageEntity

class CameraRepo(private val cameraDao: CameraDao) {

    fun insertImage(imageEntity: ImageEntity) {
        cameraDao.insertImage(imageEntity)
    }

    fun getImages(albumId: Int): LiveData<List<ImageEntity>> {
        return cameraDao.getImages(albumId)
    }
}