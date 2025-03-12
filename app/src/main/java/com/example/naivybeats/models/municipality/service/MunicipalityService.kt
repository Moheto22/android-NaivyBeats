package com.example.naivybeats.models.municipality.service

import com.example.naivybeats.models.municipality.model.MunicipalityWrapper
import retrofit2.Response
import retrofit2.http.GET

interface MunicipalityService {

    @GET("api/Municipalities")
    suspend fun getAllMunicipalitis(): Response<MunicipalityWrapper>
}