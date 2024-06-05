package com.changer.basquiat.common.data.remote

import com.changer.basquiat.domain.model.ConversionResponse
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ConversionService {
    @Multipart
    @POST("/vertopal/enviar")
    suspend fun enviarArquivo(
        @Part file: MultipartBody.Part
    ): Response<ConversionResponse>

    @POST("/vertopal/converter")
    suspend fun converterArquivo(
        @Query("extensao") extensao: String
    ): Response<ConversionResponse>

    @GET("/vertopal/url")
    suspend fun obterUrl(): Response<ResponseBody>

    @GET("/vertopal/baixar")
    suspend fun baixarArquivo(): Response<ResponseBody>
}