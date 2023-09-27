package com.bhdrburak.vbtcase.common

import android.content.Context
import android.text.TextUtils
import androidx.preference.PreferenceManager

object PrefUtil {


    fun getString(context: Context, key: String): String? {
        var value: String? = null
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        if (pref != null) {
            value = pref.getString(key, null)
        }
        return value
    }


    fun setString(context: Context, key: String, value: String?): Boolean {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)

        if (pref != null && !TextUtils.isEmpty(key)) {
            val editor = pref.edit()
            editor.putString(key, value)
            return editor.commit()
        }
        return false
    }

}