package com.example.naivybeats.models.image.service
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ImageService {
    @GET("api/Image/{path}")
    suspend fun getImageByUrl(): Response<ResponseBody>
}