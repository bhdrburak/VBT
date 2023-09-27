package com.bhdrburak.fupscase.core.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, message, duration).show()
}

fun Activity.getColorRes(@ColorRes id: Int) = ContextCompat.getColor(applicationContext, id)

fun Context.getColorRes(@ColorRes id: Int): Int = ContextCompat.getColor(applicationContext, id)

fun Fragment.showToast(message: String) =
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}