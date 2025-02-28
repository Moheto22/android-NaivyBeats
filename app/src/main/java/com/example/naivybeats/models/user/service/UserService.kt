package com.example.naivybeats.models.user.service

import com.example.naivybeats.models.user.model.UserWrapper
import com.example.naivybeats.models.user.model.Users
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("api/Users")
    fun getAllUsers(): Call<UserWrapper>
}