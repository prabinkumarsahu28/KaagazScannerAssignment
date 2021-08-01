package com.prabin.kagazscanner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.prabin.kagazscanner.databinding.ActivityGalleryBinding
import com.prabin.kagazscanner.recyclerview.GalleryAdapter
import com.prabin.kagazscanner.roomdb.CameraApplication
import com.prabin.kagazscanner.roomdb.entities.ImageEntity
import com.prabin.kagazscanner.roomdb.viewmodel.CameraViewModel
import com.prabin.kagazscanner.roomdb.viewmodel.CameraViewModelFactory

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding
    private var imgList = arrayListOf<ImageEntity>()
    private lateinit var adapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = application as CameraApplication
        val repo = application.cameraRepo
        val viewModelFactory = CameraViewModelFactory(repo)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(CameraViewModel::class.java)

        adapter = GalleryAdapter(imgList)
        binding.rvGallery.layoutManager = GridLayoutManager(this@GalleryActivity, 2)
        binding.rvGallery.adapter = adapter

        getImages(viewModel)
    }

    private fun getImages(viewModel: CameraViewModel) {
        viewModel.getImages(0).observe(this, {
            imgList.clear()
            imgList.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }
}