package com.bhdrburak.mobillium.data.repo

import com.bhdrburak.mobillium.common.Resource
import com.bhdrburak.mobillium.data.api.RetrofitAPI
import com.bhdrburak.mobillium.data.model.MovieResponse
import com.bhdrburak.mobillium.data.model.Result
import com.bhdrburak.mobillium.data.room.MovieDAO
import java.lang.Exception
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDAO: MovieDAO,
    private val retrofitAPI: RetrofitAPI
) : MovieRepository {


    override suspend fun fetchAllMovies(): Resource<MovieResponse> {
        return try {
            val response = retrofitAPI.fetchMovies()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.succes(it)
                } ?: Resource.error("Hata", null)
            } else {
                Resource.error("Hata", null)
            }
        } catch (e: Exception) {
            Resource.error("No data!", null)
        }
    }

    override suspend fun insertMovie(movie: Result) {
        movieDAO.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: Result) {
        movieDAO.deleteMovie(movie)
    }


}