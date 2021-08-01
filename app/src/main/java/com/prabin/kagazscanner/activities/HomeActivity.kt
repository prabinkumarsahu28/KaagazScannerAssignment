package com.prabin.kagazscanner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.prabin.kagazscanner.clicklisteners.AlbumClickListener
import com.prabin.kagazscanner.databinding.ActivityGalleryBinding
import com.prabin.kagazscanner.recyclerview.GalleryAdapter
import com.prabin.kagazscanner.roomdb.CameraApplication
import com.prabin.kagazscanner.roomdb.entities.AlbumEntity
import com.prabin.kagazscanner.roomdb.viewmodel.CameraViewModel
import com.prabin.kagazscanner.roomdb.viewmodel.CameraViewModelFactory

class HomeActivity : AppCompatActivity(), AlbumClickListener {

    private lateinit var binding: ActivityGalleryBinding
    private var albumList = arrayListOf<AlbumEntity>()
    private lateinit var adapter: GalleryAdapter
    var albumId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = application as CameraApplication
        val repo = application.cameraRepo
        val viewModelFactory = CameraViewModelFactory(repo)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(CameraViewModel::class.java)

        adapter = GalleryAdapter(albumList, this)
        binding.rvGallery.layoutManager = GridLayoutManager(this@HomeActivity, 2)
        binding.rvGallery.adapter = adapter

        getAlbums(viewModel)
        binding.btnOpenCamera.setOnClickListener {
            albumNameDialog(viewModel)
        }
    }


    /**
     * this functions opens a dialog box to give a name to the album.
     */
    private fun albumNameDialog(viewModel: CameraViewModel) {
        val editText = EditText(this)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Album name :-")
            .setMessage("Give a name to your album")
            .setView(editText)
            .setPositiveButton("Add") { _, _ ->
                val albumName = editText.text.toString()
                viewModel.insertAlbum(AlbumEntity(albumName, ""))
                viewModel.id.observe(this, {
                    albumId = it
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("albumId", albumId)
                    intent.putExtra("albumName", albumName)
                    startActivity(intent)
                })
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    /**
     * this function gives the list of albums.
     */
    private fun getAlbums(viewModel: CameraViewModel) {
        viewModel.getAlbums().observe(this, {
            albumList.clear()
            albumList.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    /**
     * on click of an album this function takes to another activity where we can see all
     * the images inside that album.
     */
    override fun onAlbumClicked(albumEntity: AlbumEntity) {
        Log.d("prabin2", "${albumEntity.albumId}")
        val intent = Intent(this, AlbumActivity::class.java)
        intent.putExtra("albumEntity", albumEntity)
        intent.putExtra("albumId", albumEntity.albumId)
        startActivity(intent)
    }
}