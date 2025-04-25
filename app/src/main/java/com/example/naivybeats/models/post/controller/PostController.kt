package com.example.naivybeats.models.post.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.musician.service.MusicianService
import com.example.naivybeats.models.post.model.Post
import com.example.naivybeats.models.post.model.PostDTO
import com.example.naivybeats.models.post.model.PostLike
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

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAllPosts(user_id: Int): List<PostLike> {
        return withContext(Dispatchers.IO) {
            val response = service.getAllPosts(user_id)
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun sendLike(user_id: Int, publication_id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val user_id_part = RequestBody.create("text/plain".toMediaTypeOrNull(), user_id.toString())
                val publication_id_part = RequestBody.create("text/plain".toMediaTypeOrNull(), publication_id.toString())

                val response = service.sendLike(user_id_part, publication_id_part)

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

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun sendFollow(restaurant_id: Int, musician_id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val restaurant_id_part = RequestBody.create("text/plain".toMediaTypeOrNull(), restaurant_id.toString())
                val musician_id_part = RequestBody.create("text/plain".toMediaTypeOrNull(), musician_id.toString())

                val response = service.sendFollow(restaurant_id_part, musician_id_part)

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

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun unFollow(restaurant_id: Int, musician_id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val restaurant_id_part = RequestBody.create("text/plain".toMediaTypeOrNull(), restaurant_id.toString())
                val musician_id_part = RequestBody.create("text/plain".toMediaTypeOrNull(), musician_id.toString())

                val response = service.unfollow(restaurant_id_part, musician_id_part)

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

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun unlike(user_id: Int, publication_id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val user_id_part = RequestBody.create("text/plain".toMediaTypeOrNull(), user_id.toString())
                val publication_id_part = RequestBody.create("text/plain".toMediaTypeOrNull(), publication_id.toString())

                val response = service.unlike(user_id_part, publication_id_part)

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