package com.example.naivybeats.models.user.controller

import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.BaseController
import com.example.naivybeats.models.superUser.service.SuperUserService
import com.example.naivybeats.models.user.model.Users
import com.example.naivybeats.models.user.service.UserService
import kotlin.collections.List

class UserController: BaseController() {
    private val service = RetrofitClient.createService(UserService::class.java)

    fun getAllUsers(): List<Users>?
    {
       return executeCall(service.getAllUsers())
    }
 }