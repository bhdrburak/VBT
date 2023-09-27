package com.bhdrburak.vbtcase

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bhdrburak.vbtcase.common.TestActivity
import com.bhdrburak.vbtcase.common.TestObj
import com.bhdrburak.vbtcase.common.UserData
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var uri: Uri? = intent.data

        if (uri != null){
            val parameters : List<String> = uri.pathSegments

            val param = parameters[parameters.size - 1]

            if (param == "productDetail"){
                //ürün detay sayfasını aç
            } else if (param == "login"){
                //login sayfasını aç
            } else {
                // ana sayfayı aç
            }
        }


    }



    private fun openNewActivitySeri(){
        val user = UserData("burak", 28)
        val bundle = Bundle()
        bundle.putParcelable("passdata", user)
        val intent = Intent(this, TestActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun openNewActivity(){
        val intent = Intent(this, TestActivity::class.java)
        //intent.putExtra("passdata", text)
        startActivity(intent)
    }


    private fun openNewActivityStatic(text : String){
        TestObj.value = text
        val intent = Intent(this, TestActivity::class.java)
        startActivity(intent)
    }




    /*private fun showDialog() {


        var button = findViewById<Button>(R.id.)


        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog_layout)

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.setCancelable(false)

        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation

        val okButton: Button = dialog.findViewById(R.id.okButton)
        val cancelButton: Button = dialog.findViewById(R.id.dismisButton)

        okButton.setOnClickListener {
            Toast.makeText(this, "ONAYLANDI", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        cancelButton.setOnClickListener {
            Toast.makeText(this, "Reddedildi", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }


        button.setOnClickListener {
            dialog.show()
        }

    }*/

    private fun startTextActivity() {
        val intent = Intent(this, TestActivity::class.java)
        startActivity(intent)

    }

    override fun onPause() {
        super.onPause()
        Log.d("mainactivity", "onPause: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("mainactivity", "onResume: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d("mainactivity", "onStart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("mainactivity", "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("mainactivity", "onRestart: ")
    }

}