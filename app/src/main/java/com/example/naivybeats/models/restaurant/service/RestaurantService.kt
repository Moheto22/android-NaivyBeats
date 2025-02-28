package com.example.naivybeats.models.restaurant.service

import com.example.naivybeats.models.restaurant.model.Restaurant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantService {
    @GET("api/Restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") restaurantId: Int): Response<Restaurant>
}