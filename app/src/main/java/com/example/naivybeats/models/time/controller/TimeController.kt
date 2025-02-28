package com.example.naivybeats.models.time.controller

import android.util.Log
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.BaseController
import com.example.naivybeats.models.time.model.Time
import com.example.naivybeats.models.time.service.TimeService
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TimeController: BaseController() {
    private val service = RetrofitClient.createService(TimeService::class.java)

    suspend fun getAllTimes(): List<Time> {
        return withContext(Dispatchers.IO) {  // Ejecutar en segundo plano
            try {
                val response = service.getAllTimes().execute()
                if (response.isSuccessful) {
                    response.body()?.times ?: emptyList()
                } else {
                    println("❌ Error en la API: ${response.errorBody()?.string()}")
                    emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                println("❌ Error en la llamada a la API: ${e.message}")
                emptyList()
            }
        }
    }
}