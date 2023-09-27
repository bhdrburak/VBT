package com.bhdrburak.vbtexample

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DialogActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        showActivityDialog()
    }

    private fun showActivityDialog() {

        val showDialog = findViewById<Button>(R.id.showDialogButton)
        //Create the Dialog here

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog_layout)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background))
        }
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(false)

        dialog.window?.attributes?.windowAnimations =
            R.style.DialogAnimation


        val okButton: Button = dialog.findViewById(R.id.btn_okay)
        val cancelButton: Button = dialog.findViewById(R.id.btn_cancel)

        okButton.setOnClickListener {
            Toast.makeText(this, "Okay", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        cancelButton.setOnClickListener {
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }


        showDialog.setOnClickListener {
            dialog.show()
        }


    }
}