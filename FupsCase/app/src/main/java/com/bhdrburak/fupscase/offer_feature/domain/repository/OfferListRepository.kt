package com.bhdrburak.fupscase.offer_feature.domain.repository

import com.bhdrburak.fupscase.core.domain.model.Offer
import kotlinx.coroutines.flow.Flow

interface OfferListRepository {

    suspend fun fetchOfferList(): Flow<List<Offer>>

    suspend fun fetchOfferSliderList(): Flow<List<Offer>>

}