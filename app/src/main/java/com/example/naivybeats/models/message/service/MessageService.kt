package com.example.naivybeats.models.message.service

import com.example.naivybeats.models.message.model.Message
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MessageService {
    @POST("api/Message")
    suspend fun newMessage(@Body message: Message): Response<Boolean>

}