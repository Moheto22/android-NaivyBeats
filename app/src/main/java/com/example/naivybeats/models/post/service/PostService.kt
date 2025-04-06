package com.example.naivybeats.models.post.service

import retrofit2.http.POST

interface PostService {
    @POST("api/Publication")
    suspend fun newPublication() {

    }
}