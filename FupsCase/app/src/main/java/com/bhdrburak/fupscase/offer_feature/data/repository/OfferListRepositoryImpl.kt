package com.bhdrburak.fupscase.offer_feature.data.repository

import com.bhdrburak.fupscase.R
import com.bhdrburak.fupscase.core.domain.model.Offer
import com.bhdrburak.fupscase.offer_feature.domain.repository.OfferListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OfferListRepositoryImpl @Inject constructor() : OfferListRepository {

    override suspend fun fetchOfferList(): Flow<List<Offer>> {
        return flow {
            emit(
                listOf(
                    Offer(
                        "Netflix Kampanyası",
                        "Sosyal Hesap'larını oluşturarak sevdiklerine para gönder, iste.",
                        R.drawable.offer_item,
                        "30 Haziran 2021"
                    ),
                    Offer(
                        "Spotify Kampanyası",
                        "Sosyal Hesap'larını oluşturarak sevdiklerine para gönder, iste.",
                        R.drawable.offer_item2,
                        "30 Haziran 2021"
                    ),
                    Offer(
                        "Youtube Kampanyası",
                        "Sosyal Hesap'larını oluşturarak sevdiklerine para gönder, iste.",
                        R.drawable.offer_item2,
                        "30 Haziran 2021"
                    ),
                    Offer(
                        "Amazon Kampanyası",
                        "Sosyal Hesap'larını oluşturarak sevdiklerine para gönder, iste.",
                        R.drawable.offer_item2,
                        "30 Haziran 2021"
                    )
                )
            )
        }
    }

    override suspend fun fetchOfferSliderList(): Flow<List<Offer>> {
        return flow {
            emit(
                listOf(
                    Offer(
                        "Sosyal Hesap",
                        "Sosyal Hesap'larını oluşturarak sevdiklerine para gönder, iste.",
                        R.drawable.offer_item,
                        "30 Haziran 2021"
                    ),
                    Offer(
                        "Oyun & E-PIN",
                        "Oyun servislerine hızlıca para aktarın, vakit kaybetmeyin, para gönder, iste.",
                        R.drawable.offer_item2,
                        "30 Haziran 2021"
                    )
                )
            )
        }
    }

}