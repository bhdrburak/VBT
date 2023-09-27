package com.bhdrburak.vbtcase

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class PermissionActivity : AppCompatActivity() {


    private lateinit var permissionLauncer: ActivityResultLauncher<Array<String>>

    private var isCameraPermissionGranted = false
    private var isReadPermissionGranted = false
    private var isLocationPermissionGranted = false
    private var isWritePermissionGranted = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        val button = findViewById<Button>(R.id.permissionButon)


        button.setOnClickListener {
            requestPermission()
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