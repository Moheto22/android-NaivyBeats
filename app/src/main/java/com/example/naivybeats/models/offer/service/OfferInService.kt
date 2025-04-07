package com.example.naivybeats.models.offer.service

import com.example.naivybeats.models.offer.models.OfferIn
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OfferInService {
    @GET("api/Offer_In")
    suspend fun getOffersIn(): Response<List<OfferIn>>

    @POST("api/Offer_In")
    suspend fun newOffer(@Body offerIn: OfferIn): Response<Boolean>
}