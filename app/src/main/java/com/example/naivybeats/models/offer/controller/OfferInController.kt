package com.example.naivybeats.models.offer.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.BaseController
import com.example.naivybeats.models.municipality.service.MunicipalityService
import com.example.naivybeats.models.offer.models.OfferIn
import com.example.naivybeats.models.offer.service.OfferInService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class OfferInController: BaseController() {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(OfferInService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getOffersIn(): List<OfferIn> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getOffersIn()

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

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun newOffer(offerIn: OfferIn): Boolean {
        return withContext(Dispatchers.IO) {
        /*    var json = Gson().toJson(offerIn)
            println(json)*/
            val response = service.newOffer(offerIn)
            if (response.isSuccessful) {
                true
            } else {
               false
            }
        }
    }
}