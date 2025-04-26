package com.example.naivybeats.models.offer.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.BaseController
import com.example.naivybeats.models.municipality.service.MunicipalityService
import com.example.naivybeats.models.offer.models.OfferDto
import com.example.naivybeats.models.offer.models.OfferIn
import com.example.naivybeats.models.offer.models.PostOffer
import com.example.naivybeats.models.offer.service.OfferInService
import com.example.naivybeats.models.style.model.Style
import com.example.naivybeats.models.time.model.Time
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class OfferInController: BaseController() {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(OfferInService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getOffersIn(user_id: Int): List<OfferDto> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getOffersIn(user_id)

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
    suspend fun newOffer(offerIn: OfferIn): Int? {
        return withContext(Dispatchers.IO) {
            var json = Gson().toJson(offerIn)
            println(json)
            val response = service.newOffer(offerIn)
            if (response.isSuccessful) {
                response.body()
            } else {
               0
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun postOffer(po: PostOffer): Boolean {
        return withContext(Dispatchers.IO) {
            val response = service.newPostOffer(po)

            if (response.isSuccessful) {
                true
            } else {
                false
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun responseMessage(
        offer_response: Int,
        message_id: Int,
        musician_id: Int,

    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {

                val offer_response_part = RequestBody.create("text/plain".toMediaTypeOrNull(), offer_response.toString())
                val message_id_part = RequestBody.create("text/plain".toMediaTypeOrNull(), message_id.toString())
                val musician_id_part = RequestBody.create("text/plain".toMediaTypeOrNull(), musician_id.toString())

                service.responseMessage(offer_response, message_id, musician_id)

                true
            } catch (e: Exception) {
                println("Excepción en insertMusician: ${e.message}")
                e.printStackTrace()
                false
            }
        }
    }
}