package com.bhdrburak.mobillium.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bhdrburak.mobillium.features.FavoriMovies.FavMoviesFragment
import com.bhdrburak.mobillium.features.FavoriMovies.FavMovieRecyclerViewAdapter
import com.bhdrburak.mobillium.features.MovieDetail.MovieDetailFragment
import com.bhdrburak.mobillium.features.MovieList.MovieListFragment
import com.bhdrburak.mobillium.features.MovieList.MovieRecyclerViewAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class FragmentFactory @Inject constructor(
    private var movieRecyclerViewAdapter: MovieRecyclerViewAdapter,
    private var favMovieRecyclerViewAdapter: FavMovieRecyclerViewAdapter,
    private var glide: RequestManager
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            MovieListFragment::class.java.name -> MovieListFragment(movieRecyclerViewAdapter)
            MovieDetailFragment::class.java.name -> MovieDetailFragment(glide)
            FavMoviesFragment::class.java.name -> FavMoviesFragment(favMovieRecyclerViewAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }

}