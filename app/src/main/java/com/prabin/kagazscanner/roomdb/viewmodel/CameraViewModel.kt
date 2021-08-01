package com.prabin.kagazscanner.roomdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.prabin.kagazscanner.roomdb.CameraRepo
import com.prabin.kagazscanner.roomdb.entities.ImageEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CameraViewModel(private val cameraRepo: CameraRepo):ViewModel() {

    fun insertImage(imageEntity: ImageEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            cameraRepo.insertImage(imageEntity)
        }
    }

    fun getImages(albumId: Int): LiveData<List<ImageEntity>> {
        return cameraRepo.getImages(albumId)
    }
}