package com.example.naivybeats.models.style.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.style.model.Style
import com.example.naivybeats.models.style.service.StyleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StyleController {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(StyleService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
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
