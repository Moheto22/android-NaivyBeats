package com.example.naivybeats.models.municipality.controller

import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.BaseController
import com.example.naivybeats.models.municipality.model.Province
import com.example.naivybeats.models.municipality.service.ProvinceService
import com.example.naivybeats.models.time.service.TimeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProvinceController: BaseController() {
    private val service = RetrofitClient.createService(ProvinceService::class.java)

    suspend fun getAllProvinces(): List<Province> {

        return withContext(Dispatchers.IO) {
            try {
                val response = service.getAllProvinces()

                if (response.isSuccessful) {
                    response.body()?.provinces ?: emptyList()
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