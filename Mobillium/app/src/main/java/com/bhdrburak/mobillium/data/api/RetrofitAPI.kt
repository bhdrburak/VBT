package com.bhdrburak.mobillium.data.api

import com.bhdrburak.mobillium.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("/3/movie/now_playing")
    suspend fun fetchMovies() : Response<MovieResponse>


    @POST("/3/movie/now_playing")
    suspend fun getMovie() : Response<MovieResponse>

}