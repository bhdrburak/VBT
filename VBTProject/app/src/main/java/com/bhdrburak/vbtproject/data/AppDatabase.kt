package com.bhdrburak.vbtproject.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bhdrburak.vbtproject.common.converter.IntegerListConverter
import com.bhdrburak.vbtproject.common.converter.ObjectConverter
import com.bhdrburak.vbtproject.common.converter.StringListConverter
import com.bhdrburak.vbtproject.data.model.UserModel


@Database(entities = [UserModel::class],version = 1, exportSchema = false)
@TypeConverters(value = [(StringListConverter::class), (IntegerListConverter::class), (ObjectConverter::class)])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}