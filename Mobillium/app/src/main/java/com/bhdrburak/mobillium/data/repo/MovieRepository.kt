package com.bhdrburak.mobillium.data.repo

import com.bhdrburak.mobillium.common.Resource
import com.bhdrburak.mobillium.data.model.MovieResponse
import com.bhdrburak.mobillium.data.model.Result

interface MovieRepository {

    suspend fun fetchAllMovies() : Resource<MovieResponse>

    suspend fun insertMovie(movie: Result)

    suspend fun deleteMovie(movie: Result)

}