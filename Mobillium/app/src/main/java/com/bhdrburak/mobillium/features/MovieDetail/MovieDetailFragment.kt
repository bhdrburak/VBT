package com.bhdrburak.mobillium.features.MovieDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bhdrburak.mobillium.R
import com.bhdrburak.mobillium.common.Constant
import com.bhdrburak.mobillium.data.model.Result
import com.bhdrburak.mobillium.databinding.FragmentMovieDetailBinding
import com.bumptech.glide.RequestManager
import javax.inject.Inject


class MovieDetailFragment @Inject constructor(private val glide: RequestManager) :
    Fragment(R.layout.fragment_movie_detail) {

    private var binding: FragmentMovieDetailBinding? = null

    private lateinit var viewModel: MovieDetailViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieDetailBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity())[MovieDetailViewModel::class.java]

        val movieModel: Result? = arguments?.getParcelable("movie")

        binding?.apply {
            glide.load(Constant.IMAGE_URL + movieModel?.backdrop_path).into(movieImageView)
            movie = movieModel
        }

        binding?.addFav?.setOnClickListener {
            viewModel.insertMovie(movieModel!!)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}