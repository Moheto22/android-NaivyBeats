package com.example.naivybeats.models.province.service

import com.example.naivybeats.models.province.models.City
import retrofit2.Response
import retrofit2.http.GET

interface ProvinceService {
    @GET("api/Cities")
    suspend fun getAllCities(): Response<List<City>>
}