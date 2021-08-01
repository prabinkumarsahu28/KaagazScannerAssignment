package com.prabin.kagazscanner.recyclerview

import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prabin.kagazscanner.roomdb.entities.ImageEntity
import kotlinx.android.synthetic.main.gallery_item_layout.view.*

class GalleryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(imageEntity: ImageEntity) {

        view.apply {
            Glide.with(this).load(imageEntity.imgLoc.toUri()).into(imgGallery)
            imgNameGallery.text = imageEntity.imgName
        }

    }
}