package com.bhdrburak.mobillium.features.FavoriMovies


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bhdrburak.mobillium.R
import com.bhdrburak.mobillium.common.Constant.IMAGE_URL
import com.bhdrburak.mobillium.data.model.Result
import com.bhdrburak.mobillium.databinding.MovieAdapterItemBinding
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class FavMovieRecyclerViewAdapter @Inject constructor(private val glide: RequestManager) : RecyclerView.Adapter<FavMovieRecyclerViewAdapter.MovieViewHolder>() {

    private var onItemClickListener : ((Result) -> Unit)? = null

    private val diffUtil = object : DiffUtil.ItemCallback<Result>(){
        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    fun setonItemClickListener(listener: (Result) -> Unit){
        onItemClickListener = listener
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var movies : List<Result>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieAdapterItemBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_adapter_item, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, glide, onItemClickListener!!)
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    class MovieViewHolder(
        private val binding: MovieAdapterItemBinding
    ) : RecyclerView.ViewHolder(binding.root){


        fun bind(item : Result, glide: RequestManager, movieClickListener: ((Result) -> Unit)){

            binding.apply {
                movie = item
            }

            glide.load(IMAGE_URL + item.poster_path).into(binding.movieImageView)

            binding.mainLayout.setOnClickListener {
                movieClickListener(item)
            }
        }

    }
}