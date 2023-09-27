package com.bhdrburak.vbtmoviedb.features.MovieListFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bhdrburak.vbtmoviedb.R
import com.bhdrburak.vbtmoviedb.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private var binding : FragmentMovieListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieListBinding.bind(view)


    }

}