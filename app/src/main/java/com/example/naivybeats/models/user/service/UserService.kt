package com.example.naivybeats.models.user.service

import com.example.naivybeats.models.user.model.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @GET("api/Users")
    suspend fun getAllUsers(): Response<List<Users>>

    @POST("api/Users")
    suspend fun insertMusician(user: Users): Response<Users>

}