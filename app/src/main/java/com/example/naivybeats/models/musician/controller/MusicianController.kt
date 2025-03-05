package com.example.naivybeats.models.musician.controller

import android.widget.Toast
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.activities.login.CreateDataNewUserArtistActivity
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
        return try {
            val response = service.getMusicianById(id)

            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun addMusician(musician: Musician): Musician? {
        return try {
            val response = service.newMusician(musician)
            if (response.isSuccessful) {
                response.body()

            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
