package com.bhdrburak.fupscase.offer_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhdrburak.fupscase.core.domain.model.Offer
import com.bhdrburak.fupscase.offer_feature.domain.use_case.OfferListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfferListViewModel @Inject constructor(
    private val offerUseCase: OfferListUseCase
) : ViewModel() {


    private val offers = MutableLiveData<List<Offer>>()
    val offerList: LiveData<List<Offer>>
        get() = offers

    init {
        fetchOffer()
    }


    fun fetchOffer() {
        viewModelScope.launch {
            offerUseCase.fetchOfferList().collect {
                offers.value = it
            }
        }
    }

}