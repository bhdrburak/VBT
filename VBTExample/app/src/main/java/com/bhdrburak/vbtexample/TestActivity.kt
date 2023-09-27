package com.bhdrburak.vbtexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.bhdrburak.vbtexample.common.PrefUtil
import com.bhdrburak.vbtexample.common.UserInfo
import com.bhdrburak.vbtexample.common.UserParcelable

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


        var testButton = findViewById<Button>(R.id.buttonBack)

        var testTextView = findViewById<TextView>(R.id.testTextView)


        //testTextView.text = intent.getStringExtra("passdata")

        //testTextView.text = PrefUtil.getStringPreference(this, "prefdata")

        //testTextView.text = PassData.changedValue

        /*var user = intent.getSerializableExtra("serialzable") as UserInfo?
        testTextView.text = user?.fullname*/

        var user = intent.getParcelableExtra("parcelable") as UserParcelable?

        testTextView.text = user?.firstName




        testButton.setOnClickListener {
            this.finish()
        }
    }
}