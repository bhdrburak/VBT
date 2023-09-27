package com.bhdrburak.vbtcase

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.VideoView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class PickImageActivity : AppCompatActivity() {



    private lateinit var permissionLauncer: ActivityResultLauncher<Array<String>>

    private var isCameraPermissionGranted = false
    private var isReadPermissionGranted = false
    private var isLocationPermissionGranted = false
    private var isWritePermissionGranted = false


    private lateinit var imageView : ImageView
    private var imageUri: Uri? = null
    private var isGallery = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_image)

        val button = findViewById<Button>(R.id.takeImageButton)

        val buttonGallery = findViewById<Button>(R.id.takeImageGalleryButton)

        imageView = findViewById(R.id.imageView)

        button.setOnClickListener {
            pickImageFromCamera()
        }

        buttonGallery.setOnClickListener {
            pickImageFromGallery()
        }


        permissionLauncer =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {

                isCameraPermissionGranted =
                    it[Manifest.permission.CAMERA] ?: isCameraPermissionGranted

                isReadPermissionGranted =
                    it[Manifest.permission.READ_EXTERNAL_STORAGE] ?: isReadPermissionGranted

                isWritePermissionGranted =
                    it[Manifest.permission.WRITE_EXTERNAL_STORAGE] ?: isWritePermissionGranted

                isLocationPermissionGranted =
                    it[Manifest.permission.ACCESS_FINE_LOCATION] ?: isLocationPermissionGranted

            }


        requestPermission()

    }



    fun pickImageFromGallery(){
        isGallery = true
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startForResult.launch(gallery)
    }

    private fun pickImageFromCamera(){


        isGallery = false
        var values = ContentValues()

        values.put(MediaStore.Images.Media.TITLE, "Yeni Foto")
        values.put(MediaStore.Images.Media.DESCRIPTION, "açıklama")

        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        camera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        camera.putExtra("type", "camera")
        startForResult.launch(camera)

    }


    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            if(isGallery){
                imageUri = intent?.data
            }
            imageView.setImageURI(imageUri)

        }
    }


    private fun requestPermission() {
        isCameraPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED


        isReadPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED


        isWritePermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED


        isLocationPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val permissionRequest: MutableList<String> = ArrayList()

        if (!isReadPermissionGranted){
            permissionRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (!isWritePermissionGranted){
            permissionRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (!isCameraPermissionGranted){
            permissionRequest.add(Manifest.permission.CAMERA)
        }

        if (!isLocationPermissionGranted){
            permissionRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        if (permissionRequest.isNotEmpty()){
            permissionLauncer.launch(permissionRequest.toTypedArray())
        }

    }
}