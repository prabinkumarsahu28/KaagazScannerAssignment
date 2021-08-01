package com.prabin.kagazscanner.activities

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment.DIRECTORY_PICTURES
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.common.util.concurrent.ListenableFuture
import com.prabin.kagazscanner.databinding.ActivityMainBinding
import com.prabin.kagazscanner.recyclerview.GalleryAdapter
import com.prabin.kagazscanner.roomdb.CameraApplication
import com.prabin.kagazscanner.roomdb.entities.ImageEntity
import com.prabin.kagazscanner.roomdb.viewmodel.CameraViewModel
import com.prabin.kagazscanner.roomdb.viewmodel.CameraViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var camera: Camera? = null
    private var preview: Preview? = null
    private var capture: ImageCapture? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = application as CameraApplication
        val repo = application.cameraRepo
        val viewModelFactory = CameraViewModelFactory(repo)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(CameraViewModel::class.java)

        cameraPermission()
        binding.btnCapture.setOnClickListener {
            takePhoto(viewModel)
        }

        binding.galleryImage.setOnClickListener {
            startActivity(Intent(this, GalleryActivity::class.java))
        }
    }

    private fun takePhoto(viewModel: CameraViewModel) {
        val photoFile = File(
            getExternalFilesDir(DIRECTORY_PICTURES),
            "KaagazScanner${System.currentTimeMillis()}.png"
        )
        val output = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        capture?.takePicture(output, ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val imgLoc = Uri.fromFile(photoFile)
                    Glide.with(this@MainActivity).load(imgLoc).into(gallery_image)
                    Log.d("prabin", "$imgLoc")
                    val imgName = photoFile.name.toString()
                    val timeStamp = System.currentTimeMillis()
                    viewModel.insertImage(ImageEntity(0, imgName, imgLoc.toString(), timeStamp))
                    Toast.makeText(this@MainActivity, "Image Saved", Toast.LENGTH_SHORT).show()
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(this@MainActivity, "Error occurred", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun cameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PERMISSION_GRANTED
        ) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                100
            )
        }
    }

    private fun startCamera() {
        val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
            ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            preview = Preview.Builder().build()
            preview?.setSurfaceProvider(binding.cameraView.surfaceProvider)
            capture = ImageCapture.Builder().build()
            val cameraSelector =
                CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
            cameraProvider.unbindAll()
            camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, capture)
        }, ContextCompat.getMainExecutor(this))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PERMISSION_GRANTED
        ) {
            startCamera()
        } else {
            Toast.makeText(this, "Accept the camera permissions", Toast.LENGTH_SHORT).show()
        }
    }
}