package com.example.naivybeats.models.municipality.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.BaseController
import com.example.naivybeats.models.municipality.model.Municipality
import com.example.naivybeats.models.municipality.service.MunicipalityService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MunicipalityController: BaseController() {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(MunicipalityService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAllMunicipality(): List<Municipality> {

        return withContext(Dispatchers.IO) {
            try {
                val response = service.getAllMunicipalitis()

                if (response.isSuccessful) {
                    response.body()?: emptyList()
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