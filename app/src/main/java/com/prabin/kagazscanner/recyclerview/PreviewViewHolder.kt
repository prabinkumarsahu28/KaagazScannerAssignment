package com.prabin.kagazscanner.recyclerview

import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prabin.kagazscanner.roomdb.entities.ImageEntity
import kotlinx.android.synthetic.main.main_item_layout.view.*

class PreviewViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(imageEntity: ImageEntity) {
        Glide.with(view).load(imageEntity.imgLoc.toUri()).into(view.ivHorImg)
    }
}