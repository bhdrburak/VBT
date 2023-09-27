package com.bhdrburak.vbtexample.common

import android.content.Context
import android.content.Context.MODE_WORLD_READABLE
import android.content.SharedPreferences
import android.text.TextUtils
import androidx.preference.PreferenceManager


object PrefUtil {

    val PREF_KEY_APP_AUTO_START = "PREF_KEY_APP_AUTO_START"

    //commit senkron, apply asenkron

    fun getBooleanPreference(context: Context?, key: String?, defaultValue: Boolean): Boolean {
        var value = defaultValue
        val preferences = PreferenceManager.getDefaultSharedPreferences(context!!)
        if (preferences != null) {
            value = preferences.getBoolean(key, defaultValue)
        }
        return value
    }

    fun writeBoolean(context: Context?, key: String?, value: Boolean): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context!!)
        if (preferences != null) {
            val editor = preferences.edit()
            editor.putBoolean(key, value)
            return editor.commit()
        }
        return false
    }


    fun getStringPreference(context: Context?, key: String?): String? {
        var value: String? = null
        val preferences = PreferenceManager.getDefaultSharedPreferences(context!!)
        if (preferences != null) {
            value = preferences.getString(key, null)
        }
        return value
    }

    fun setStringPreference(context: Context?, key: String?, value: String?): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context!!)
        if (preferences != null && !TextUtils.isEmpty(key)) {
            val editor = preferences.edit()
            editor.putString(key, value)
            return editor.commit()
        }
        return false
    }

    fun clearStringPreference(context: Context?, key: String?) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context!!)
        preferences.edit().remove(key).apply()
    }

    fun clearStringPreference(context: Context?) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context!!)
        preferences.edit().clear().apply()
    }
}