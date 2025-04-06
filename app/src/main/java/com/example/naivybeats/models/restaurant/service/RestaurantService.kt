package com.example.naivybeats.models.restaurant.service

import com.example.naivybeats.models.restaurant.model.Restaurant
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface RestaurantService {
    @GET("api/Restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") restaurantId: Int): Response<Restaurant>

    @POST("api/Restaurants")
    suspend fun newRestaurant(@Part("user_id") userId: RequestBody,
                              @Part("name") name: RequestBody,
                              @Part file: MultipartBody.Part,
                              @Part("email") email: RequestBody,
                              @Part("password") password: RequestBody,
                              @Part("phone_number") phone_number: RequestBody,
                              @Part("province_id") municipality_id: RequestBody,
                              @Part("latitud") latitud: RequestBody,
                              @Part("longitud") longitud: RequestBody,
                              @Part("description") description: RequestBody,
                              @Part("opening_time") opening_time: RequestBody,
                              @Part("closing_time") closing_time: RequestBody
    ): Response<Boolean>
}