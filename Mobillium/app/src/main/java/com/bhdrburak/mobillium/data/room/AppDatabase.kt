package com.bhdrburak.mobillium.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bhdrburak.mobillium.common.converters.IntegerListConverter
import com.bhdrburak.mobillium.common.converters.StringListConverter
import com.bhdrburak.mobillium.data.model.Result

@Database(entities = [Result::class], version = 1)
@TypeConverters(value = [(StringListConverter::class), (IntegerListConverter::class)])
abstract class AppDatabase : RoomDatabase(){
    abstract fun movieDao() : MovieDAO

    companion object {

        val migration = object : Migration(13,14) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE movie ADD COLUMN movieId 333444"
                )
            }
        }

    }
}