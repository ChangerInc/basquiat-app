package com.changer.basquiat.common.data.remote

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import java.util.UUID

interface ConversionService {
    @Multipart
    @POST("/vertopal/enviar")
    suspend fun enviarArquivo(
        @Part file: MultipartBody.Part,
        @Query("user") user: UUID?
    ): Response<String>

    @POST("/vertopal/converter")
    suspend fun converterArquivo(
        @Query("extensao") extensao: String
    ): Response<String>

    @GET("/vertopal/url")
    suspend fun obterUrl(): Response<String>

    @GET("/vertopal/baixar")
    suspend fun baixarArquivo(): Response<ByteArray>
}