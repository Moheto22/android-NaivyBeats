package com.example.naivybeats.models.offer.service

import com.example.naivybeats.models.message.model.Message
import com.example.naivybeats.models.offer.models.OfferIn
import com.example.naivybeats.models.offer.models.OfferDto
import com.example.naivybeats.models.offer.models.PostOffer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface OfferInService {
    @GET("api/Offer_In/{user_id}")
    suspend fun getOffersIn(@Path("user_id") user_id: Int): Response<List<OfferDto>>

    @POST("api/Offer_In")
    suspend fun newOffer(@Body offerIn: OfferIn): Response<Int>

    @POST("api/post_offer")
    suspend fun newPostOffer(@Body po: PostOffer): Response<Boolean>

    @Multipart
    @POST("api/Offer_In/Response")
    suspend fun responseMessage(@Part("offer_response") offer_response: Int,
                                    @Part("message_id") message_id: Int,
                                    @Part("musician_id") musician_id: Int): Response<List<Message>>
}