package com.example.naivybeats.models.time.controller

import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.time.model.Time
import com.example.naivybeats.models.user.service.UserService
import com.example.naivybeats.models.time.service.TimeService

class TimeController
{
    private val service = RetrofitClient.createService(TimeService::class.java)

    suspend fun getAllTimes(): List<Time> {
        return service.getAllTimes()
    }
}