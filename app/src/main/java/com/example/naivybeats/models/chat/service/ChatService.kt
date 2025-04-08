package com.example.naivybeats.models.chat.service

import com.example.naivybeats.models.chat.model.Chat
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatService {
    @POST("api/Chat")
    suspend fun newChat(@Body chat: Chat): Response<Boolean>

    @GET("api/Chat")
    suspend fun getChatByMusicianAndRestaurantId(@Body chat: Chat): Response<Chat>
}