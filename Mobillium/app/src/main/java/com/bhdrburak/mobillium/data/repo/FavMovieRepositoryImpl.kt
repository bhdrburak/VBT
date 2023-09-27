package com.bhdrburak.mobillium.data.repo

import androidx.lifecycle.LiveData
import com.bhdrburak.mobillium.data.model.Result
import com.bhdrburak.mobillium.data.room.MovieDAO
import javax.inject.Inject

class FavMovieRepositoryImpl @Inject constructor(
    private val movieDAO: MovieDAO
) : FavMovieRepository {


    override fun fetchAllMoviesFromDB(): LiveData<List<Result>> {
        val result = movieDAO.getAllMovies()
        return result
    }

    override suspend fun insertMovie(movie: Result) {
        movieDAO.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: Result) {
        movieDAO.deleteMovie(movie)
    }
}