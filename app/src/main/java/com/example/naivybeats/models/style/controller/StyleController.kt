package com.example.naivybeats.models.style.controller

import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.style.model.Style
import com.example.naivybeats.models.style.service.StyleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StyleController {
    private val service = RetrofitClient.createService(StyleService::class.java)

    suspend fun getAllStyles(): List<Style>{
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getAllStyles()
                response.body()?: emptyList()

            } catch (e: Exception) {
                e.printStackTrace()
                println("❌ Error en la llamada a la API: ${e.message}")
                emptyList()
            }
        }
    }
}
