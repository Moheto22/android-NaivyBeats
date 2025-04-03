package com.example.naivybeats.models.time.controller

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(TimeService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAllTimes(): List<Time> {
        return withContext(Dispatchers.IO) {
            try {

                val response = service.getAllTimes()
                response.body()?: emptyList()

            } catch (e: Exception) {
                e.printStackTrace()
                println("❌ Error en la llamada a la API: ${e.message}")
                emptyList()
            }
        }
    }
}