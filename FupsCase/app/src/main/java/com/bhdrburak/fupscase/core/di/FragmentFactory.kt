package com.bhdrburak.fupscase.core.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bhdrburak.fupscase.login_feature.presentation.LoginFragment
import com.bhdrburak.fupscase.login_feature.presentation.OfferSliderAdapter
import com.bhdrburak.fupscase.offer_feature.presentation.OfferListFragment
import com.bhdrburak.fupscase.offer_feature.presentation.OfferRecyclerAdapter
import javax.inject.Inject

class FragmentFactory @Inject constructor(
    private val offerSliderAdapter: OfferSliderAdapter,
    private val offerRecyclerAdapter: OfferRecyclerAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            LoginFragment::class.java.name -> LoginFragment(offerSliderAdapter)
            OfferListFragment::class.java.name -> OfferListFragment(offerRecyclerAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}