package com.example.naivybeats.models.musician.controller

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.activities.login.CreateDataNewUserArtistActivity
import com.example.naivybeats.models.BaseController
import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.musician.service.MusicianService
import com.example.naivybeats.models.style.model.Style
import com.example.naivybeats.models.time.model.Time
import com.example.naivybeats.models.time.service.TimeService
import com.example.naivybeats.models.user.model.Users
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.File
import java.math.BigDecimal

class MusicianController {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(MusicianService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getMusicianById(id: Int): Musician? {
        return try {
            val response = service.getMusicianById(id)

            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun insertMusician(
        user_id: Int,
        name: String,
        photo: File,
        email: String,
        password: String,
        phone_number: String,
        province_id: Int,
        latitud: BigDecimal,
        longitud: BigDecimal,
        description: String,
        styles: List<Style>,
        times: List<Time>
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val gson = Gson()
                val stylesJson = gson.toJson(styles)
                val timesJson = gson.toJson(times)

                val userIdPart = RequestBody.create("text/plain".toMediaTypeOrNull(), user_id.toString())
                val namePart = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
                val photoPart = MultipartBody.Part.createFormData("multimedia_content", photo.name, photo.asRequestBody("image/png".toMediaTypeOrNull()))
                val emailPart = RequestBody.create("text/plain".toMediaTypeOrNull(), email)
                val passwordPart = RequestBody.create("text/plain".toMediaTypeOrNull(), password)
                val phonePart = RequestBody.create("text/plain".toMediaTypeOrNull(), phone_number)
                val provinceIdPart = RequestBody.create("text/plain".toMediaTypeOrNull(), province_id.toString())
                val latitudPart = RequestBody.create("text/plain".toMediaTypeOrNull(), latitud.toString())
                val longitudPart = RequestBody.create("text/plain".toMediaTypeOrNull(), longitud.toString())
                val descriptionPart = RequestBody.create("text/plain".toMediaTypeOrNull(), description)
                val stylesPart = RequestBody.create("application/json".toMediaTypeOrNull(), stylesJson)
                val timesPart = RequestBody.create("application/json".toMediaTypeOrNull(), timesJson)
                service.insertMusician(userIdPart, namePart, photoPart, emailPart,
                                        passwordPart, phonePart, provinceIdPart, latitudPart, longitudPart,
                                        descriptionPart, stylesPart, timesPart)
                true
            } catch (e: Exception) {
                println("Excepción en insertMusician: ${e.message}")
                e.printStackTrace()
                false
            }
        }
    }
}

