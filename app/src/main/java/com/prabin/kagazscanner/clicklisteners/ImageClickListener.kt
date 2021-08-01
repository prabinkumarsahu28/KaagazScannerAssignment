package com.prabin.kagazscanner.clicklisteners

import com.prabin.kagazscanner.roomdb.entities.ImageEntity

interface ImageClickListener {
    fun onImageClicked(imageEntity: ImageEntity)
}