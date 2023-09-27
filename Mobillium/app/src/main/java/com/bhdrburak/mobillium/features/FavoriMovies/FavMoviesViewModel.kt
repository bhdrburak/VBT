package com.bhdrburak.mobillium.features.FavoriMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhdrburak.mobillium.common.Resource
import com.bhdrburak.mobillium.data.model.MovieResponse
import com.bhdrburak.mobillium.data.model.Result
import com.bhdrburak.mobillium.data.repo.FavMovieRepository
import com.bhdrburak.mobillium.data.room.MovieDAO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavMoviesViewModel @Inject constructor(private val favMovieRepository: FavMovieRepository) : ViewModel() {

    val movieList = favMovieRepository.fetchAllMoviesFromDB()

    fun insertMovie(movie : Result) {

        viewModelScope.launch {
            favMovieRepository.insertMovie(movie)
        }
    }

    fun deleteMovie(movie: Result){
        viewModelScope.launch {
            favMovieRepository.deleteMovie(movie)
        }
    }


}