package com.example.naivybeats.models.musician.service

import com.example.naivybeats.models.musician.model.Musician
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MusicianService {

    @GET("api/Musicians/{id}")
    suspend fun getMusicianById(@Path("id") musicianId: Int): Response<Musician>

    @POST("api/Musicians")
    suspend fun newMusician(@Body musician: Musician): Response<Musician>
}