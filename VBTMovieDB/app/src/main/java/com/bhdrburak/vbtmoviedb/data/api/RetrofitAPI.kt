package com.bhdrburak.vbtmoviedb.data.api

import com.bhdrburak.vbtmoviedb.common.Resource
import com.bhdrburak.vbtmoviedb.data.model.MovieResponse
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("/3/movie/now_playing")
    suspend fun fetchMovies() : Resource<MovieResponse>


}