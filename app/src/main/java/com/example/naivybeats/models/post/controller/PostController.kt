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

class PostController {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(PostService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun insertPost(postDTO: PostDTO): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val json = Gson().toJson(postDTO)
                println(json)
                service.newPublication(postDTO)
                true

            } catch (e: Exception) {
                println("Excepción en insertMusician: ${e.message}")
                e.printStackTrace()
                false
            }
        }
    }
}