package com.prabin.kagazscanner.recyclerview

import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prabin.kagazscanner.clicklisteners.AlbumClickListener
import com.prabin.kagazscanner.roomdb.entities.AlbumEntity
import kotlinx.android.synthetic.main.gallery_item_layout.view.*

class GalleryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(albumEntity: AlbumEntity, albumClickListener: AlbumClickListener) {

        view.apply {
            Glide.with(this).load(albumEntity.albumImg.toUri()).into(imgGallery)
            imgNameGallery.text = albumEntity.albumName
            cvAlbum.setOnClickListener {
                albumClickListener.onAlbumClicked(albumEntity)
            }
        }
    }
}