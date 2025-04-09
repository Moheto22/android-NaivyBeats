package com.example.naivybeats.models.message.service

import com.example.naivybeats.models.chat.model.Chat
import com.example.naivybeats.models.message.model.Message
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MessageService {
    @POST("api/Message")
    suspend fun newMessage(@Body message: Message): Response<Boolean>

    @GET("api/Message/{chat_id}")
    suspend fun getMessagesByChatId(@Path("user_id") chat_id: Int): Response<List<Message>>


}