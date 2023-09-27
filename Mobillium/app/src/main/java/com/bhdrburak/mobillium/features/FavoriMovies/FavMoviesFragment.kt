package com.bhdrburak.mobillium.features.FavoriMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhdrburak.mobillium.R
import com.bhdrburak.mobillium.databinding.FragmentFavMoviesBinding
import javax.inject.Inject


class FavMoviesFragment @Inject constructor(private val movieRecyclerViewAdapter: FavMovieRecyclerViewAdapter): Fragment(R.layout.fragment_fav_movies) {

    private var binding : FragmentFavMoviesBinding? = null

    private lateinit var viewModel : FavMoviesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavMoviesBinding.bind(view)


        viewModel = ViewModelProvider(requireActivity())[FavMoviesViewModel::class.java]


        //viewModel.fetchFavMovies()

        initRecyclerView()
        subscribeToobservers()
    }



    private fun subscribeToobservers() {
        viewModel.movieList.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()){
                movieRecyclerViewAdapter.movies = it
            }
        }
    }


    private fun initRecyclerView() {
        binding?.movieListRecyclerView?.adapter = movieRecyclerViewAdapter

        binding?.movieListRecyclerView?.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )

        movieRecyclerViewAdapter.setonItemClickListener {

        }

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}