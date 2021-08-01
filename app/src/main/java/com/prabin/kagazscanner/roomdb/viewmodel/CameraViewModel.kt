package com.prabin.kagazscanner.roomdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prabin.kagazscanner.roomdb.CameraRepo
import com.prabin.kagazscanner.roomdb.entities.AlbumEntity
import com.prabin.kagazscanner.roomdb.entities.ImageEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CameraViewModel(private val cameraRepo: CameraRepo) : ViewModel() {
    var id: MutableLiveData<Long> = MutableLiveData()

    fun insertImage(imageEntity: ImageEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            cameraRepo.insertImage(imageEntity)
        }
    }

    fun getImages(albumId: Long): LiveData<List<ImageEntity>> {
        return cameraRepo.getImages(albumId)
    }

    fun insertAlbum(albumEntity: AlbumEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            id.postValue(cameraRepo.insertAlbum(albumEntity))
        }
    }

    fun getAlbums(): LiveData<List<AlbumEntity>> {
        return cameraRepo.getAlbums()
    }

    fun updateAlbumImg(albumEntity: AlbumEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            cameraRepo.updateAlbumImg(albumEntity)
        }
    }
}