package com.example.naivybeats.models.message.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.chat.model.Chat
import com.example.naivybeats.models.message.model.Message
import com.example.naivybeats.models.message.service.MessageService
import com.example.naivybeats.models.municipality.service.MunicipalityService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException

class MessageController {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(MessageService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun newMessage(message: Message): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.newMessage(message)

                if (response.isSuccessful) {
                    true
                } else {
                    false
                }
            } catch (e: HttpException) {
                println("Error HTTP: ${e.code()} - ${e.message()}")
                false
            } catch (e: IOException) {
                println("Error de red: ${e.message}")
                false
            } catch (e: Exception) {
                // Captura cualquier otro tipo de error
                println("Error inesperado: ${e.message}")
                false
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getMessagesById(chat_id: Int): List<Message> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getMessagesByChatId(chat_id)

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