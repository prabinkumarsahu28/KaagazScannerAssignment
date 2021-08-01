package com.prabin.kagazscanner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.prabin.kagazscanner.clicklisteners.ImageClickListener
import com.prabin.kagazscanner.databinding.ActivityAlbumBinding
import com.prabin.kagazscanner.recyclerview.AlbumAdapter
import com.prabin.kagazscanner.roomdb.CameraApplication
import com.prabin.kagazscanner.roomdb.entities.AlbumEntity
import com.prabin.kagazscanner.roomdb.entities.ImageEntity
import com.prabin.kagazscanner.roomdb.viewmodel.CameraViewModel
import com.prabin.kagazscanner.roomdb.viewmodel.CameraViewModelFactory

class AlbumActivity : AppCompatActivity(), ImageClickListener {

    private lateinit var binding: ActivityAlbumBinding
    private lateinit var adapter: AlbumAdapter
    private var imageList = arrayListOf<ImageEntity>()
    private var albumId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent != null && intent.extras != null) {
            val albumEntity: AlbumEntity? = intent.getParcelableExtra("albumEntity")
            supportActionBar?.title = albumEntity?.albumName
            albumId = intent.getLongExtra("albumId", 0)
            Log.d("prabin2", "$albumId")
        }

        val application = application as CameraApplication
        val repo = application.cameraRepo
        val viewModelFactory = CameraViewModelFactory(repo)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(CameraViewModel::class.java)

        getImages(viewModel)

        adapter = AlbumAdapter(imageList, this)
        binding.rvAlbum.layoutManager = GridLayoutManager(this, 2)
        binding.rvAlbum.adapter = adapter
    }

    private fun getImages(viewModel: CameraViewModel) {
        viewModel.getImages(albumId!!).observe(this, {
            imageList.clear()
            imageList.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onImageClicked(imageEntity: ImageEntity) {
        val intent = Intent(this, ImageActivity::class.java)
        intent.putExtra("img", imageEntity.imgLoc)
        startActivity(intent)
    }
}