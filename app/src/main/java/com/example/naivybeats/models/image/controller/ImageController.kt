package com.example.naivybeats.models.image.controller

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.naivybeats.RetrofitClient
import com.example.naivybeats.models.image.service.ImageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class ImageController {
    @RequiresApi(Build.VERSION_CODES.O)
    private val service = RetrofitClient.createService(ImageService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getImage(path: String): File {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getImageByUrl(path)

                if (response.isSuccessful && response.body() != null) {
                    val responseBody = response.body()

                    val file = File.createTempFile("image", ".png") // Puedes ajustar la extensión según el tipo de archivo

                    responseBody?.let { body ->
                        val inputStream = body.byteStream()
                        val outputStream = FileOutputStream(file)
                        inputStream.use { input ->
                            outputStream.use { output ->
                                input.copyTo(output)
                            }
                        }
                    }

                    file

                } else {
                    throw Exception("Error en la respuesta del servidor: ${response.code()}")
                }
            } catch (e: Exception) {
                throw Exception("Error al descargar la imagen: ${e.message}")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun updateImage(file: File, path: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val photoPart = MultipartBody.Part.createFormData("photo", file.name, file.asRequestBody("image/png".toMediaTypeOrNull()))
                val path = RequestBody.create("text/plain".toMediaTypeOrNull(), path)

                val response = service.updateImage(photoPart, path)
                if (response.isSuccessful && response.body() != null) {
                    val responseBody = response.body()

                    true
                } else {
                    throw Exception("Error en la respuesta del servidor: ${response.code()}")
                }

            } catch (e: Exception) {
                throw Exception("Error al descargar la imagen: ${e.message}")
            }
        }
    }
}