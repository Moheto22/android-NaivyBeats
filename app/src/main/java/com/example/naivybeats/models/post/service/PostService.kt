package com.example.naivybeats.models.post.service

import com.example.naivybeats.models.post.model.PostDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.Part

interface PostService {

    @Multipart
    @POST("api/Publication")
    suspend fun newPublication(
        @Part("title") title: RequestBody,
        @Part("user_id") userId: RequestBody,
        @Part("description") description: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<Boolean>
}