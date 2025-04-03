package com.example.naivybeats.models.musician.controller

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.activities.login.CreateDataNewUserArtistActivity
import com.example.naivybeats.models.BaseController
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.musician.service.MusicianService
import com.example.naivybeats.models.time.service.TimeService
import com.example.naivybeats.models.user.model.Users
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class MusicianController {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(MusicianService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun insertMusician(musician: Musician): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val json = Gson().toJson(musician)
                println(json)
                service.insertMusician(musician)
                true

            } catch (e: Exception) {
                println("Excepción en insertMusician: ${e.message}")
                e.printStackTrace()
                false
            }
        }
    }
}
