package com.example.naivybeats.models

import retrofit2.Call
import retrofit2.Response

open class BaseController {

    fun <T> executeCall(call: Call<T>): T? {
        return try {
            val response = call.execute()

            if (response.isSuccessful) {
                response.body()
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Error desconocido"
                println("Error en la respuesta: $errorMessage")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error en la llamada a la API: ${e.message}")
            null
        }
    }

    fun sendRequest(call: Call<Void>): Boolean {
        return try {
            val response = call.execute()
            if (response.isSuccessful) {
                println("Operación exitosa")
                true
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Error desconocido"
                println("Error en la respuesta: $errorMessage")
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error en la llamada a la API: ${e.message}")
            false
        }
    }

}