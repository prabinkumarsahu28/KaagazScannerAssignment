package com.prabin.kagazscanner.roomdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prabin.kagazscanner.roomdb.CameraRepo

class CameraViewModelFactory(private val repo: CameraRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CameraViewModel(repo) as T
    }
}