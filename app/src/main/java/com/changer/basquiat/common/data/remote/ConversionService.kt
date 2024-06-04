package com.changer.basquiat.common.data.remote

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import java.util.UUID

interface ConversionService {
    @Multipart
    @POST("/vertopal/enviar/{idUsuario}")
    suspend fun enviarArquivo(
        @Part file: MultipartBody.Part,
        @Path("idUsuario") user: UUID?
    ): Response<ResponseBody>

    @POST("/vertopal/converter")
    suspend fun converterArquivo(
        @Query("extensao") extensao: String
    ): Response<ResponseBody>

    @GET("/vertopal/url")
    suspend fun obterUrl(): Response<ResponseBody>

    @GET("/vertopal/baixar")
    suspend fun baixarArquivo(): Response<ByteArray>
}