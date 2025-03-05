package com.example.naivybeats

import com.example.naivybeats.models.superUser.service.SuperUserService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.3.191/NaivyBeats/"

    private val retrofit: Retrofit by lazy {
        val gson = GsonBuilder()
            .create() // Usamos el GsonBuilder para una configuración más flexible si es necesario

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)) // Usamos el converter
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        ///return retrofit.create(service)
        return retrofit.create(service)
    }
}