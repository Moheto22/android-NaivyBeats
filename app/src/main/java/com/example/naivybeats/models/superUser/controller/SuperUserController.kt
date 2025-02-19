package com.example.naivybeats.models.superUser.controller

import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.superUser.model.SuperUser
import com.example.naivybeats.models.superUser.service.SuperUserService
import retrofit2.Call

class SuperUserController {
    private val service = RetrofitClient.createService(SuperUserService::class.java)

    suspend fun getSuperUsers(): List<SuperUser>? {
        return try {
            service.getSuperUsers()

        } catch (e: Exception) {
            null
        }
    }
}