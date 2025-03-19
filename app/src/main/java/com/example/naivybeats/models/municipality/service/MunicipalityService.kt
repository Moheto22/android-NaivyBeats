package com.example.naivybeats.models.municipality.service

import com.example.naivybeats.models.municipality.model.Municipality
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface MunicipalityService {

    @GET("api/Municipalities")
    suspend fun getAllMunicipalitis(): Response<List<Municipality>>

}