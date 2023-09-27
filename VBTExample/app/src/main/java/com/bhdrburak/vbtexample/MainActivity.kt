package com.bhdrburak.vbtexample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bhdrburak.vbtexample.common.PrefUtil
import com.bhdrburak.vbtexample.common.UserInfo
import com.bhdrburak.vbtexample.common.UserParcelable


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var testButton = findViewById<Button>(R.id.testButton)

        testButton.setOnClickListener {
            startNewActivityCustomView()
        }

    }

    fun startNewActivity(){
        val intent = Intent(this, TestActivity::class.java)
        intent.putExtra("passdata", "bahadır burak karaoğlu")
        startActivity(intent)
    }



    fun startNewActivityCustomView(){
        val intent = Intent(this, CustomActivity::class.java)
        intent.putExtra("passdata", "bahadır burak karaoğlu")
        startActivity(intent)
    }



    fun startNewActivityWithBundle(){
        val userInfo = UserInfo()
        userInfo.username = "bhdrburak"
        userInfo.fullname = "burak"
        userInfo.age = 28

        val parcelUser = UserParcelable("burak", "karaoğlu")
        val intent = Intent(this, TestActivity::class.java)
        //val bundle = bundleOf("bundleData" to "testBundle")
        val bundle = Bundle()
        //bundle.putSerializable("serialzable", userInfo)

        bundle.putParcelable("parcelable", parcelUser)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun startNewActivityWithPref(){
        PrefUtil.setStringPreference(this, "prefdata", "kotlin dersi")
        val intent = Intent(this, TestActivity::class.java)
        startActivity(intent)
    }


    fun startDialogActivity(){
        val intent = Intent(this, DialogActivity::class.java)
        intent.putExtra("passdata", "bahadır burak karaoğlu")
        startActivity(intent)
    }
}