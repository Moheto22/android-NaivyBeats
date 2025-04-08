package com.example.naivybeats.models.chat.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.chat.model.Chat
import com.example.naivybeats.models.chat.service.ChatService
import com.example.naivybeats.models.municipality.service.MunicipalityService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChatController {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(ChatService::class.java)

    suspend fun newChat(chat: Chat): Boolean {
        return withContext(Dispatchers.IO) {
            val response = service.newChat(chat)

            if (response.isSuccessful) {
                true
            } else {
                false
            }
        }
    }
}