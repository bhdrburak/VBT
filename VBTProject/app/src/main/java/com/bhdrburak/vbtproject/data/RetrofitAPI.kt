package com.bhdrburak.vbtproject.data

import com.bhdrburak.vbtproject.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitAPI {

    @GET("/users/{user}/getAll")
    suspend fun fetchAllUser(@Path("user") username: String): Response<List<UserModel>>


    @GET("/users/{user}")
    suspend fun fetchUser(@Path("user") username : String): Response<UserModel>

}