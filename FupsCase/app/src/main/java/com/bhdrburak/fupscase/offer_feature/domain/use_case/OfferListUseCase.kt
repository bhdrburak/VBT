package com.bhdrburak.fupscase.offer_feature.domain.use_case

import com.bhdrburak.fupscase.core.domain.model.Offer
import com.bhdrburak.fupscase.offer_feature.domain.repository.OfferListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfferListUseCase @Inject constructor(private val repository: OfferListRepository) {

    suspend fun fetchOfferList(): Flow<List<Offer>> {
        return repository.fetchOfferList()
    }

}