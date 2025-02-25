package com.example.naivybeats.models.time.controller

import android.util.Log
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.BaseController
import com.example.naivybeats.models.time.model.Time
import com.example.naivybeats.models.time.service.TimeService

class TimeController: BaseController() {
    private val service = RetrofitClient.createService(TimeService::class.java)

    fun getAllTimes(): List<Time> {
        val response = executeCall(service.getAllTimes())
        return response?.times?: emptyList()
    }
}