package com.example.naivybeats.models.restaurant.controller

import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.restaurant.model.Restaurant
import com.example.naivybeats.models.restaurant.service.RestaurantService

class RestaurantController {
    private val service = RetrofitClient.createService(RestaurantService::class.java)

    suspend fun getRestaurantById(id: Int): Restaurant? {
        val response = service.getRestaurantById(id)
        return response.body()
    }
}