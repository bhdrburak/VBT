package com.bhdrburak.vbtproject.common.converter

import androidx.room.TypeConverter
import com.bhdrburak.vbtproject.data.model.UserModel
import com.google.gson.Gson

class ObjectConverter {

    @TypeConverter
    fun fromStatesHolder(owner: UserModel): String {
        return Gson().toJson(owner)
    }
    @TypeConverter
    fun toStatesHolder(owner: String): UserModel {
        return Gson().fromJson(owner,UserModel::class.java)
    }
}