package com.bhdrburak.mobillium.features.MovieList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.bhdrburak.mobillium.R
import com.bhdrburak.mobillium.common.Status
import com.bhdrburak.mobillium.common.workers.DataTrackingWorker
import com.bhdrburak.mobillium.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment @Inject constructor(private val movieRecyclerViewAdapter: MovieRecyclerViewAdapter) :
    Fragment(R.layout.fragment_movie_list) {

    private var binding: FragmentMovieListBinding? = null

    private lateinit var viewModel: MovieListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieListBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity())[MovieListViewModel::class.java]

        initRecyclerView()
        subscribeToobservers()

        binding?.showFavs?.setOnClickListener {
            findNavController().navigate(R.id.action_movieListFragment_to_favMoviesFragment)
        }

        viewModel.fetchMovies()

        startWorker()


    }

    private fun startWorker() {

        GlobalScope.launch(Dispatchers.Main){

            val dataBuilder = Data.Builder()
            dataBuilder.putString("data", "testdata")
            dataBuilder.putInt("type", 1)

            val worker = OneTimeWorkRequestBuilder<DataTrackingWorker>()
                .setInitialDelay(1, TimeUnit.SECONDS)
                .setInputData(dataBuilder.build())
                .build()


            WorkManager.getInstance(requireContext())
                .enqueueUniqueWork("datatracking", ExistingWorkPolicy.KEEP, worker)
        }
    }

    private fun subscribeToobservers() {
        viewModel.movieList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding!!.progressBar.visibility = View.GONE
                    movieRecyclerViewAdapter.movies = it.data!!.results
                }

                Status.ERROR -> {
                    binding!!.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                Status.LOADING -> {
                    binding!!.progressBar.visibility = View.VISIBLE
                }
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
            val bundle = bundleOf("movie" to it)
            findNavController().navigate(
                R.id.action_movieListFragment_to_movieDetailFragment,
                bundle
            )
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}