package com.example.naivybeats.models.municipality.controller

import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.BaseController
import com.example.naivybeats.models.municipality.model.Municipality
import com.example.naivybeats.models.municipality.service.MunicipalityService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MunicipalityController: BaseController() {
    private val service = RetrofitClient.createService(MunicipalityService::class.java)

    suspend fun getAllMunicipality(): List<Municipality> {

        return withContext(Dispatchers.IO) {
            try {
                val response = service.getAllMunicipalitis()

                if (response.isSuccessful) {
                    response.body()?.municipalities ?: emptyList()
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