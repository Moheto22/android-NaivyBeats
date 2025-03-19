package com.example.naivybeats.models.province.controller

import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.province.models.City
import com.example.naivybeats.models.province.service.ProvinceService
import com.example.naivybeats.models.time.model.Time
import com.example.naivybeats.models.time.service.TimeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProvinceController {
    private val service = RetrofitClient.createService(ProvinceService::class.java)

    suspend fun getAllCities(): List<City> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getAllCities()
                response.body()?: emptyList()

            } catch (e: Exception) {
                e.printStackTrace()
                println("❌ Error en la llamada a la API: ${e.message}")
                emptyList()
            }
        }
    }
}
