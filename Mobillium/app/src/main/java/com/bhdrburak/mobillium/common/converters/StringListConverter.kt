package com.bhdrburak.mobillium.common.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class StringListConverter {
    @TypeConverter
    fun fromString(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson<List<String>>(value, listType)
    }

    fun fromList(list: List<String>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}