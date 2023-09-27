package com.bhdrburak.mobillium.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bhdrburak.mobillium.data.model.Result

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(result : Result)

    @Delete
    suspend fun deleteMovie(result: Result)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Result>>

}