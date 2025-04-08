package com.example.naivybeats.models.image.service
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.message.service.MessageService
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageService {

    @GET("api/Image/{path}")
    suspend fun getImageByUrl(@Path("path") path: String): Response<ResponseBody>
}