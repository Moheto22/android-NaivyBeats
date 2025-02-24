package com.example.naivybeats.models.time.service

import com.example.naivybeats.models.time.model.Time
import retrofit2.http.GET

interface TimeService
{
    @GET("api/Time")
    fun getAllTimes(): List<Time>
}