package com.bhdrburak.fupscase.login_feature.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bhdrburak.fupscase.core.domain.model.Offer
import com.bhdrburak.fupscase.databinding.OfferSliderItemBinding
import com.bumptech.glide.RequestManager
import javax.inject.Inject


class OfferSliderAdapter @Inject constructor(
    val glide: RequestManager
) :
    RecyclerView.Adapter<OfferSliderAdapter.SliderViewHolder>() {


    private val diffUtil = object : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var offerList: List<Offer>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            OfferSliderItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), glide
        )
    }


    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val offer = offerList.get(position)
        holder.bind(offer)
    }

    override fun getItemCount(): Int {
        return offerList.size
    }

    class SliderViewHolder(
        private val binding: OfferSliderItemBinding,
        private val glide: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Offer
        ) {
            binding.apply {
                glide.load(item.resource).into(offerImage)
                offer = item
            }
        }
    }
}