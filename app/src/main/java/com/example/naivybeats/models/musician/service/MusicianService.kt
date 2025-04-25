package com.example.naivybeats.models.musician.service

import com.example.naivybeats.models.musician.model.Musician
import com.example.naivybeats.models.user.model.Users
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface MusicianService {

    @GET("api/Musicians/{id}")
    suspend fun getMusicianById(@Path("id") musicianId: Int): Response<Musician?>

    @Multipart
    @POST("api/Musicians")
    suspend fun insertMusician(@Part("user_id") userId: RequestBody,
                               @Part("name") name: RequestBody,
                               @Part file: MultipartBody.Part,
                               @Part("email") email: RequestBody,
                               @Part("password") password: RequestBody,
                               @Part("phone_number") phone_number: RequestBody,
                               @Part("province_id") municipality_id: RequestBody,
                               @Part("latitud") latitud: RequestBody,
                               @Part("longitud") longitud: RequestBody,
                               @Part("styles") styles: RequestBody,
                               @Part("times") times: RequestBody
                              ): Response<Boolean>

    @GET("api/Musicians/Follows/{user_id}")
    suspend fun getFollows(@Path("user_id") musicianId: Int): Response<Int>

    @GET("api/Musicians/Likes/{user_id}")
    suspend fun getLikes(@Path("user_id") musicianId: Int): Response<Int>
}