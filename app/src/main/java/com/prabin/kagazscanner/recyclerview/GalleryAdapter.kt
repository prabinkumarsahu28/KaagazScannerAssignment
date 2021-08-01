package com.prabin.kagazscanner.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prabin.kagazscanner.R
import com.prabin.kagazscanner.roomdb.entities.ImageEntity

class GalleryAdapter(private val imageList: List<ImageEntity>) :
    RecyclerView.Adapter<GalleryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.gallery_item_layout, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.setData(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}