package com.example.naivybeats.models.restaurant.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.restaurant.model.Restaurant
import com.example.naivybeats.models.restaurant.service.RestaurantService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.math.BigDecimal

class RestaurantController {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(RestaurantService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getRestaurantById(id: Int): Restaurant? {
        val response = service.getRestaurantById(id)
        return response.body()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun newRestaurant(user_id: Int,
                              name: String,
                              photo: File,
                              email: String,
                              password: String,
                              phone_number: String,
                              province_id: Int,
                              latitud: String,
                              longitud: String,
                              opening_time: String,
                              closing_time: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val userIdPart = RequestBody.create("text/plain".toMediaTypeOrNull(), user_id.toString())
                val namePart = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
                val photoPart = MultipartBody.Part.createFormData("photo", photo.name, photo.asRequestBody("image/png".toMediaTypeOrNull()))
                val emailPart = RequestBody.create("text/plain".toMediaTypeOrNull(), email)
                val passwordPart = RequestBody.create("text/plain".toMediaTypeOrNull(), password)
                val phonePart = RequestBody.create("text/plain".toMediaTypeOrNull(), phone_number)
                val provinceIdPart = RequestBody.create("text/plain".toMediaTypeOrNull(), province_id.toString())
                val latitudPart = RequestBody.create("text/plain".toMediaTypeOrNull(), latitud)
                val longitudPart = RequestBody.create("text/plain".toMediaTypeOrNull(), longitud)
                val opening_time = RequestBody.create("text/plain".toMediaTypeOrNull(), opening_time)
                val closing_time = RequestBody.create("text/plain".toMediaTypeOrNull(), closing_time)
                service.newRestaurant(userIdPart, namePart, photoPart, emailPart, passwordPart,
                                    phonePart, provinceIdPart, latitudPart, longitudPart,
                                    opening_time, closing_time)
                true

            } catch (e: Exception) {
                println("Excepción en insertMusician: ${e.message}")
                e.printStackTrace()
                false
            }
        }
    }
}