package com.example.naivybeats.models.superUser.service;
import com.example.naivybeats.models.superUser.model.SuperUser;

import retrofit2.Call;
import retrofit2.http.GET;

interface SuperUserService {
    @GET("api/Super_User")
    suspend fun getSuperUsers(): List<SuperUser>
}