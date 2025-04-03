package com.example.naivybeats.models.restaurant.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.restaurant.model.Restaurant
import com.example.naivybeats.models.restaurant.service.RestaurantService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RestaurantController {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(RestaurantService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getRestaurantById(id: Int): Restaurant? {
        val response = service.getRestaurantById(id)
        return response.body()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun newRestaurant(restaurant: Restaurant): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val json = Gson().toJson(restaurant)
                println(json)
                service.newRestaurant(restaurant)
                true

            } catch (e: Exception) {
                println("Excepción en insertMusician: ${e.message}")
                e.printStackTrace()
                false
            }
        }
    }
}