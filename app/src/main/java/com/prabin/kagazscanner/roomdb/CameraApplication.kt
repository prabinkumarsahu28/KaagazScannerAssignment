package com.prabin.kagazscanner.roomdb

import android.app.Application

class CameraApplication : Application() {

    private val cameraDao by lazy {
        val database = CameraDb.getInstance(this)
        database.getCameraDao()
    }

    val cameraRepo by lazy {
        CameraRepo(cameraDao)
    }
}