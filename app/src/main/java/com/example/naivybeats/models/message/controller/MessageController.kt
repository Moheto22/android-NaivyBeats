package com.example.naivybeats.models.message.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.message.model.Message
import com.example.naivybeats.models.message.service.MessageService
import com.example.naivybeats.models.municipality.service.MunicipalityService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MessageController {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(MessageService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun newMessage(message: Message): Boolean {
        return withContext(Dispatchers.IO) {
            val response = service.newMessage(message)

            if (response.isSuccessful) {
                true
            } else {
                false
            }
        }
    }
}