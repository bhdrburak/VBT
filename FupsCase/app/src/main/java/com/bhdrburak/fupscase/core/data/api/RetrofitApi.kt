package com.bhdrburak.fupscase.core.data.api

import com.bhdrburak.fupscase.core.data.model.OfferDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitAPI {

    @GET("/users/{user}/repos")
    suspend fun fetchUserRepo(@Path("user") username: String): Response<List<OfferDTO>>


    @GET("/users/{user}")
    suspend fun fetchUser(@Path("user") username: String): Response<OfferDTO>

}