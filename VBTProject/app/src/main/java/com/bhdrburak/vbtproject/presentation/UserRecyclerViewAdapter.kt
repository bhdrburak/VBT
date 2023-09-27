package com.bhdrburak.vbtproject.presentation

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bhdrburak.vbtproject.data.model.UserModel
import com.bhdrburak.vbtproject.databinding.UserAdapterItemBinding
import javax.inject.Inject


class UserRecyclerViewAdapter @Inject constructor() : RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder>() {


    private var onItemClickListener : ((UserModel) -> Unit)? = null
    private var onFavIconClickListener : ((UserModel) -> Unit)? = null

    private val diffUtil = object : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }


    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var users: List<UserModel>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    fun setOnItemClickListener(listener : (UserModel) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnFavIconClickListener(listener : (UserModel) -> Unit) {
        onFavIconClickListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserAdapterItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user, onItemClickListener, onFavIconClickListener)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UserViewHolder(
        private val binding: UserAdapterItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: UserModel,
            userClickListener: ((UserModel) -> Unit)?,
            favClickListener: ((UserModel) -> Unit)?,
        ) {
            binding.apply {
                repo = item
                executePendingBindings()
            }

            setFavIcon(item)

            binding.favImageView.setOnClickListener {
                item.isFav = !item.isFav
                setFavIcon(item)

                favClickListener?.let {
                    it(item)
                }
            }


            binding.mainLayout.setOnClickListener {
                userClickListener?.let {
                    it(item)
                }
            }

        }

        private fun setFavIcon(item: UserModel){

            if (item.isFav) {
                binding.favImageView.setColorFilter(Color.argb(255, 220, 192, 8))
            } else {
                binding.favImageView.setColorFilter(Color.argb(255, 220, 220, 220))
            }
        }

    }
}