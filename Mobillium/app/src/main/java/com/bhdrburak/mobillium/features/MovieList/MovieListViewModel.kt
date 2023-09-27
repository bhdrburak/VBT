package com.bhdrburak.mobillium.features.MovieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhdrburak.mobillium.common.Resource
import com.bhdrburak.mobillium.data.model.MovieResponse
import com.bhdrburak.mobillium.data.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val movies = MutableLiveData<Resource<MovieResponse>>()
    val movieList : LiveData<Resource<MovieResponse>>
        get() = movies


    fun fetchMovies(){
        movies.value = Resource.loading(null)
        viewModelScope.launch {
            val result = repository.fetchAllMovies()
            movies.value = result
        }
    }

}