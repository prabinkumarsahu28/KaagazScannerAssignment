package com.prabin.kagazscanner.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prabin.kagazscanner.R
import com.prabin.kagazscanner.roomdb.entities.ImageEntity

class PreviewAdapter(private val imageList: List<ImageEntity>) :
    RecyclerView.Adapter<PreviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.main_item_layout, parent, false)
        return PreviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: PreviewViewHolder, position: Int) {
        holder.setData(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}