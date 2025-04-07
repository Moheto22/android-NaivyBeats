package com.example.naivybeats.models.user.service

import com.example.naivybeats.models.user.model.Users
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @GET("api/Users")
    suspend fun getAllUsers(): Response<List<Users>>

    @GET("api/Users/{name}")
    suspend fun getUserIdByName(@Path("name") name: String): Response<Int>

   /* @GET("api/Image/{path}")
    suspend fun getImage(@Path("path") path: String): Response<HttpResponseMessage>*/



}