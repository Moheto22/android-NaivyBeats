package com.example.naivybeats.models.offer.service

import com.example.naivybeats.models.offer.models.OfferIn
import com.example.naivybeats.models.offer.models.OfferDto
import com.example.naivybeats.models.offer.models.PostOffer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OfferInService {
    @GET("api/Offer_In/{user_id}")
    suspend fun getOffersIn(@Path("user_id") user_id: Int): Response<List<OfferDto>>

    @POST("api/Offer_In")
    suspend fun newOffer(@Body offerIn: OfferIn): Response<Boolean>

    @POST("api/post_offer")
    suspend fun newPostOffer(@Body po: PostOffer): Response<Boolean> 
}