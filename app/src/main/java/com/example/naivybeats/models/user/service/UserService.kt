package com.example.naivybeats.models.user.service

import com.example.naivybeats.models.user.model.Users
import retrofit2.http.GET

interface UserService {
    @GET("api/Users")
    suspend fun getAllUsers(): List<Users>
}