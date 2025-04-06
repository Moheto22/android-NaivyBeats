package com.example.naivybeats.models.user.controller

import android.os.Build
import androidx.annotation.RequiresApi
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
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(UserService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getUserIdByName(name: String): Int? {
        return withContext(Dispatchers.IO) {
            try {
                var userId: Int? = 0

                val response = service.getUserIdByName(name)

                if (!response.isSuccessful) {
                    println("❌ Error en la llamada a la API: ${response.code()} - ${response.message()}")
                    userId = 0 // Asignar 0 en caso de error
                } else {
                    userId = response.body()
                    if (userId == null) {
                        println("❌ El cuerpo de la respuesta está vacío.")
                        userId = 0
                    }
                }
                return@withContext userId
            } catch (e: Exception) {
                e.printStackTrace()
                println("❌ Error en la llamada a la API: ${e.message}")
                return@withContext 0
            }
        }
    }
 }