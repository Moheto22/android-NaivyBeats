package com.example.naivybeats

import com.example.naivybeats.models.superUser.service.SuperUserService
import com.google.gson.GsonBuilder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.1.153/NaivyBeats/"
    // private const val BASE_URL = "http://10.0.0.99/dam04/"

    private val retrofit: Retrofit by lazy {
        val gson = GsonBuilder()
            .create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }
}