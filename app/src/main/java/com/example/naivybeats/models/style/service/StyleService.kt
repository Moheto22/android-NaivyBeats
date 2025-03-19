package com.example.naivybeats.models.style.service
import com.example.naivybeats.models.style.model.Style
import retrofit2.Response
import retrofit2.http.GET

interface StyleService {
    @GET("api/Styles")
    suspend fun getAllStyles(): Response<List<Style>>

}