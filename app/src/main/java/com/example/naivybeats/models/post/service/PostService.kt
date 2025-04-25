package com.example.naivybeats.models.post.service

import com.example.naivybeats.models.post.model.Post
import com.example.naivybeats.models.post.model.PostDTO
import com.example.naivybeats.models.post.model.PostLike
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Path

interface PostService {

    @GET("api/Publication/{user_id}")
    suspend fun getAllPosts(@Path("user_id") user_id: Int): Response<List<PostLike>>

    @Multipart
    @POST("api/Publication")
    suspend fun newPublication(
        @Part("title") title: RequestBody,
        @Part("user_id") userId: RequestBody,
        @Part("description") description: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<Boolean>

    @Multipart
    @POST("api/Like")
    suspend fun sendLike(
        @Part("user_id") user_id: RequestBody,
        @Part("publication_id") publication_id: RequestBody,
    ) : Response<Boolean>

    @Multipart
    @POST("api/Follow")
    suspend fun sendFollow(
        @Part("restaurant_id") user_id: RequestBody,
        @Part("musician_id") musician_id: RequestBody,
    ) : Response<Boolean>
}