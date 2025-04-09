package com.example.naivybeats.models.chat.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.chat.model.Chat
import com.example.naivybeats.models.chat.service.ChatService
import com.example.naivybeats.models.municipality.service.MunicipalityService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChatController {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(ChatService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getChatByMusicianAndRestaurantId(chat: Chat): Chat? {
        return withContext(Dispatchers.IO) {
            try {

                val json = Gson().toJson(chat)
                println("JSON enviado: $json")

                val response = service.getChatByMusicianAndRestaurantId(chat.musicianId, chat.restaurantId)

                if (response.isSuccessful && response.body() != null) {
                    response.body()
                } else {
                    println("Error en la respuesta: ${response.code()}")
                    null
                }
            } catch (e: Exception) {
                println("Error al realizar la solicitud: ${e.message}")
                null
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getChatByUserId(user_id: Int): List<Chat> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getChatByUserId(user_id)

                if (response.isSuccessful) {
                    response.body() ?: emptyList()
                } else {
                    emptyList()
                }
            } catch (e: Exception) {
                println(e.message)
                emptyList()
            }
        }
    }
}