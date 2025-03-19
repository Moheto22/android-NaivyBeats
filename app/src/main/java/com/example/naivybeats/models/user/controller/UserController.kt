package com.example.naivybeats.models.user.controller

import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.BaseController
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.superUser.service.SuperUserService
import com.example.naivybeats.models.user.model.Users
import com.example.naivybeats.models.user.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.collections.List

class UserController: BaseController() {
    private val service = RetrofitClient.createService(UserService::class.java)

    suspend fun getAllUsers(): List<Users>
    {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getAllUsers()
                response.body()?: emptyList()

            } catch (e: Exception) {
                e.printStackTrace()
                println("❌ Error en la llamada a la API: ${e.message}")
                emptyList()
            }
        }
    }

    suspend fun insertMusician(user: Users): Users {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.insertMusician(user)
                if (response.isSuccessful) {
                    response.body()
                } else{
                    null
                }
            } catch (e: Exception) {
                null
            }!!
        }
    }
 }