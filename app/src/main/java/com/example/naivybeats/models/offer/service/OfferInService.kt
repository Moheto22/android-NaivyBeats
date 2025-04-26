package com.example.naivybeats.models.offer.service

import com.example.naivybeats.models.message.model.Message
import com.example.naivybeats.models.offer.models.OfferIn
import com.example.naivybeats.models.offer.models.OfferDto
import com.example.naivybeats.models.offer.models.PostOffer
import okhttp3.RequestBody
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
    suspend fun responseMessage(@Part("offer_response") offer_response: RequestBody,
                                    @Part("message_id") message_id: RequestBody,
                                    @Part("musician_id") musician_id: RequestBody): Response<List<Message>>

    @Multipart
    @GET("api/Offer_In/ListOffers")
    suspend fun getListOffers(@Part("restaurant_id") restaurant_id: RequestBody,
                              @Part("musician_id") musician_id: RequestBody): Response<List<OfferIn>>
}