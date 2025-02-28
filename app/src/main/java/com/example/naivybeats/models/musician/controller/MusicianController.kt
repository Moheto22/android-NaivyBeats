package com.example.naivybeats.models.musician.controller

import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.BaseController
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.musician.service.MusicianService
import com.example.naivybeats.models.time.service.TimeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class MusicianController {
    private val service = RetrofitClient.createService(MusicianService::class.java)

    suspend fun getMusicianById(id: Int): Musician? {
        val response = service.getMusicianById(id)
        return response.body()
    }

    suspend fun addMusician(musician: Musician): Musician? {
        val response = service.newMusician(musician)
        return response.body()
    }
}
