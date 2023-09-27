package com.bhdrburak.fupscase.offer_feature.di

import com.bhdrburak.fupscase.offer_feature.data.repository.OfferListRepositoryImpl
import com.bhdrburak.fupscase.offer_feature.domain.repository.OfferListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class OfferRepositoryModule {

    @Binds
    abstract fun providesLoginRepository(
        offerListRepositoryImpl: OfferListRepositoryImpl
    ): OfferListRepository

}