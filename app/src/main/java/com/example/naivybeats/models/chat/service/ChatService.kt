package com.example.naivybeats.models.chat.service

import com.example.naivybeats.models.chat.model.Chat
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatService {
    @POST("api/Chat")
    suspend fun newChat(@Body chat: Chat): Response<Boolean>

    @GET("api/Chat/{musician_id}/{restaurant_id}")
    suspend fun getChatByMusicianAndRestaurantId(@Path("musician_id") musician_id: Int, @Path("restaurant_id") restaurant_id: Int): Response<Chat>
}