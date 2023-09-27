package com.bhdrburak.fupscase.login_feature.domain.use_case

import com.bhdrburak.fupscase.core.domain.model.Offer
import com.bhdrburak.fupscase.offer_feature.domain.repository.OfferListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfferSliderUseCase @Inject constructor(private val repository: OfferListRepository) {

    suspend fun fetchOfferSliderList(): Flow<List<Offer>> {
        return repository.fetchOfferSliderList()
    }

}