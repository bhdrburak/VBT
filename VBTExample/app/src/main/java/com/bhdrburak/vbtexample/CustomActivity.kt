package com.bhdrburak.vbtexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class CustomActivity : AppCompatActivity() {

    private lateinit var customButtonWithProgressBar: ButtonWithProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom)



        customButtonWithProgressBar = findViewById(R.id.customButton)

        setButton()

        customButtonWithProgressBar.setOnClickListener {
            customButtonWithProgressBar.setBackgroundResource(R.drawable.empty_button_stroke)
            customButtonWithProgressBar.showLoader()
            Handler().postDelayed({
                customButtonWithProgressBar.setText("Başarılı")
                customButtonWithProgressBar.hideLoader()
                customButtonWithProgressBar.setBackgroundResource(R.drawable.bt_enable_bg)
            }, 1000)
        }

    }

    private fun setButton() {
        customButtonWithProgressBar.setEnable(true)
        customButtonWithProgressBar.setText("Başla")

        customButtonWithProgressBar.setBackgroundResource(R.drawable.empty_button_stroke)
        customButtonWithProgressBar.hideLoader()
    }


}