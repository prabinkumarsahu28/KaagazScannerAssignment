package com.prabin.kagazscanner.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prabin.kagazscanner.R
import com.prabin.kagazscanner.clicklisteners.ImageClickListener
import com.prabin.kagazscanner.roomdb.entities.ImageEntity

class AlbumAdapter(
    private val imageList: List<ImageEntity>,
    private val imageClickListener: ImageClickListener
) : RecyclerView.Adapter<AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.gallery_item_layout, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.setData(imageList[position], imageClickListener)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}