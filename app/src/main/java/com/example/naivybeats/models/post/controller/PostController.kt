package com.example.naivybeats.models.post.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.musician.service.MusicianService
import com.example.naivybeats.models.post.model.PostDTO
import com.example.naivybeats.models.post.service.PostService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PostController {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(PostService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun insertPost(
        title: String,
        userId: Int,
        description: String,
        file: File
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val titlePart = RequestBody.create("text/plain".toMediaTypeOrNull(), title)
                val userIdPart = RequestBody.create("text/plain".toMediaTypeOrNull(), userId.toString())
                val descriptionPart = RequestBody.create("text/plain".toMediaTypeOrNull(), description)
                val filePart = MultipartBody.Part.createFormData(
                    "multimedia_content",
                    file.name,
                    file.asRequestBody("image/png".toMediaTypeOrNull())
                )

                val response = service.newPublication(titlePart, userIdPart, descriptionPart, filePart)

                if (response.isSuccessful && response.body() == true) {
                    true
                } else {
                    false
                }
            } catch (e: Exception) {
                println("Excepción en insertPost: ${e.message}")
                e.printStackTrace()
                false
            }
        }
    }
}