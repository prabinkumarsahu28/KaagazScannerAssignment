package com.prabin.kagazscanner.clicklisteners

import com.prabin.kagazscanner.roomdb.entities.AlbumEntity

interface AlbumClickListener {
    fun onAlbumClicked(albumEntity: AlbumEntity)
}