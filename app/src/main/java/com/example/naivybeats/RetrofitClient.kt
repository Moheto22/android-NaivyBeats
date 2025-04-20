package com.example.naivybeats

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.models.superUser.service.SuperUserService
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
object RetrofitClient {
    //private const val BASE_URL = "http://10.0.0.133/NaivyBeats/"
    //private const val BASE_URL = "http://10.0.0.99/dam04/"
    private const val BASE_URL = "http://192.168.0.12/NaivyBeats/"

    private val retrofit: Retrofit by lazy {
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalTime::class.java, JsonSerializer<LocalTime> { src, _, _ ->
                JsonPrimitive(src.format(DateTimeFormatter.ofPattern("HH:mm"))) // Formato HH:mm
            })
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
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