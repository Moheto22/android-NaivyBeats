package com.example.naivybeats.models.image.service
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.message.service.MessageService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ImageService {

    @GET("api/Image/{path}")
    suspend fun getImageByUrl(@Path("path") path: String): Response<ResponseBody>

    @PUT("api/Image")
    suspend fun updateImage(@Part file: MultipartBody.Part,
                            @Part("path") path: RequestBody,
                            @Part("user_name") user_name: RequestBody,
                            @Part("description") description: RequestBody,
                            @Part("user_id") user_id: RequestBody): Response<ResponseBody>
}