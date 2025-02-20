package com.example.naivybeats.models.user.controller

import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.superUser.service.SuperUserService
import com.example.naivybeats.models.user.model.Users
import com.example.naivybeats.models.user.service.UserService

class UserController {
    private val service = RetrofitClient.createService(UserService::class.java)

    suspend fun getAllUsers(): List<Users>
    {
       return service.getAllUsers()
    }
}