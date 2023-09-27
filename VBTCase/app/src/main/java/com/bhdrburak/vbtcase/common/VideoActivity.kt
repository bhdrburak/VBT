package com.bhdrburak.vbtcase.common

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Video
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.bhdrburak.vbtcase.R

class VideoActivity : AppCompatActivity() {

    private lateinit var videoView : VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val button = findViewById<Button>(R.id.takeVideoButton)

        videoView = findViewById(R.id.videoView)

        val mediaController = MediaController(this)

        videoView.setMediaController(mediaController)

        button.setOnClickListener {
            startVideo()
        }

    }

    private fun startVideo(){
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startForResult.launch(intent)
    }


    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            videoView.setVideoURI(intent?.data)
            videoView.start()
        }
    }
}