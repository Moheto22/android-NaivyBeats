package com.example.naivybeats.models.offerInStyles.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OfferInStylesService {
    @GET("api/Offer_in_Styles/{id}")
    suspend fun getStylesByOfferInId(@Path("id") id: Int): Response<List<String>>

}