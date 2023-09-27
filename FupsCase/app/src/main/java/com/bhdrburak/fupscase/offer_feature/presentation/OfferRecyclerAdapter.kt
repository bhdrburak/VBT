package com.bhdrburak.fupscase.offer_feature.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bhdrburak.fupscase.core.domain.model.Offer
import com.bhdrburak.fupscase.databinding.OfferListItemBinding
import javax.inject.Inject

class OfferRecyclerAdapter @Inject constructor() :
    RecyclerView.Adapter<OfferRecyclerAdapter.RecyclerViewHolder>() {


    private var onItemClickListener: ((Offer) -> Unit)? = null

    private val diffUtil = object : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem == newItem
        }

    }


    fun setOnItemClickListener(listener: (Offer) -> Unit) {
        onItemClickListener = listener
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var offerList: List<Offer>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            OfferListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val offer = offerList.get(position)
        holder.bind(offer, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return offerList.size
    }

    class RecyclerViewHolder(
        private val binding: OfferListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Offer,
            offerClickListener: ((Offer) -> Unit)?,
        ) {
            binding.apply {
                offer = item
                layoutMain.setOnClickListener {
                    offerClickListener.let {

                    }
                }
            }
        }
    }
}