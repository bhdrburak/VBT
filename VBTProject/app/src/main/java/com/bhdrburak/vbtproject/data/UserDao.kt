package com.bhdrburak.vbtproject.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bhdrburak.vbtproject.data.model.UserModel


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : UserModel)

    @Delete
    suspend fun deleteUser(user: UserModel)

    @Query("SELECT * FROM repos")
    fun getAllUser(): LiveData<List<UserModel>>

}