package com.example.naivybeats.models.post.service

import com.example.naivybeats.models.post.model.PostDTO
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.Body

interface PostService {
    @POST("api/Publication")
    suspend fun newPublication(@Body postDto: PostDTO): Response<Boolean>
}