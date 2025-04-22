package com.example.naivybeats.models.offerInStyles.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.offer.models.PostOffer
import com.example.naivybeats.models.offerInStyles.service.OfferInStylesService
import com.example.naivybeats.models.post.service.PostService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OfferInStylesController {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(OfferInStylesService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getStylesByOfferInId(id: Int): List<String> {
        return withContext(Dispatchers.IO) {
            val response = service.getStylesByOfferInId(id)

            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        }
    }
}