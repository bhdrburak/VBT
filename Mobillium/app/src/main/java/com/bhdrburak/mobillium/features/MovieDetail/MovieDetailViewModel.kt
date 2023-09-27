package com.bhdrburak.mobillium.features.MovieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhdrburak.mobillium.data.model.Result
import com.bhdrburak.mobillium.data.repo.FavMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val favMovieRepository: FavMovieRepository) : ViewModel() {


    private val movies = MutableLiveData<List<Result>>()
    val movieList : LiveData<List<Result>>
        get() = movies



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