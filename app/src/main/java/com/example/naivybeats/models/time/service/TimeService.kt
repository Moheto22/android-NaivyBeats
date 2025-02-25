package com.example.naivybeats.models.time.service

import com.example.naivybeats.models.time.model.TimeWrapper
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface TimeService
{
    @GET("api/times")
    fun getAllTimes(): Call<TimeWrapper>
}