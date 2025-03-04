package com.example.naivybeats.models.municipality.service

import com.example.naivybeats.models.municipality.model.ProvinceWrapper
import retrofit2.Response
import retrofit2.http.GET

interface ProvinceService {

    @GET("api/provinces")
    suspend fun getAllProvinces(): Response<ProvinceWrapper>
}