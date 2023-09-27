package com.bhdrburak.mobillium.data.repo

import androidx.lifecycle.LiveData
import com.bhdrburak.mobillium.data.model.Result

interface FavMovieRepository {


    fun fetchAllMoviesFromDB() : LiveData<List<Result>>

    suspend fun insertMovie(movie: Result)

    suspend fun deleteMovie(movie: Result)

}