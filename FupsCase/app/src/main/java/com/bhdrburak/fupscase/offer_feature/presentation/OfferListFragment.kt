package com.bhdrburak.fupscase.offer_feature.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhdrburak.fupscase.R
import com.bhdrburak.fupscase.core.domain.model.Offer
import com.bhdrburak.fupscase.databinding.FragmentOfferListBinding
import javax.inject.Inject

class OfferListFragment @Inject constructor(private val offerRecyclerAdapter: OfferRecyclerAdapter) :
    Fragment(R.layout.fragment_offer_list) {


    private var fragmentBinding: FragmentOfferListBinding? = null

    lateinit var viewModel: OfferListViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(requireActivity())[OfferListViewModel::class.java]

        fragmentBinding = FragmentOfferListBinding.bind(view)

        fragmentBinding!!.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        initRecyclerAdapter()
        subscribeToObservers()
    }


    private fun openOfferDetail(offer: Offer) {

    }

    private fun initRecyclerAdapter() {

        fragmentBinding!!.offerListRecycler.adapter = offerRecyclerAdapter

        fragmentBinding!!.offerListRecycler.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )


        offerRecyclerAdapter.setOnItemClickListener {
            openOfferDetail(it)
        }
    }


    private fun subscribeToObservers() {

        viewModel.offerList.observe(viewLifecycleOwner) {
            offerRecyclerAdapter.offerList = it
        }


    }


    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }

}